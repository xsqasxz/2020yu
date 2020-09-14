package com.small.security.service;

import com.small.constant.SuccessStaticState;
import com.small.entity.after.AfterAbility;
import com.small.entity.after.AfterPower;
import com.small.entity.after.AfterUser;
import com.small.security.dao.SysRoleDao;
import com.small.security.dao.SysUserDao;
import com.small.security.entity.SysUser;
import com.small.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 自定义密码校验和帐号切换
 * @since 2020年5月8日 09:46:28
 * @author xueshiqi
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.debug("登录校验");
        String username = authentication.getName();
        String presentedPassword = (String)authentication.getCredentials();
        logger.debug("用户名：{}",username);
        logger.debug("用户名密码：{}",presentedPassword);
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
//        redisTemplate.delete(SuccessStaticState.H5SESSIONUSER + details.getRemoteAddress());
//        redisTemplate.delete(SuccessStaticState.H5SESSIONUSERIP + details.getRemoteAddress());
//        redisTemplate.delete(SuccessStaticState.H5SESSIONUSER + details.getRemoteAddress() + username);
        if(isRedisLoginIntercept(SuccessStaticState.H5SESSIONUSERIP + details.getRemoteAddress(),5)){
            throw new BadCredentialsException("您的设备已经被锁定了，请一小时后在进行尝试！");
        }
        if(isRedisLoginIntercept(SuccessStaticState.H5SESSIONUSER + details.getRemoteAddress(),10)){
            throw new BadCredentialsException("您的设备已经被锁定了，请一小时后在进行尝试！");
        }
        if(isRedisLoginIntercept(SuccessStaticState.H5SESSIONUSER + details.getRemoteAddress() + username,5)){
            throw new BadCredentialsException("您的设备已经被锁定了，请5分钟后在进行尝试！");
        }
        // 根据用户名获取用户信息
        AfterUser afterUser = sysUserDao.findByName(username);
        if (afterUser == null) {
            //这里做ip拦截 登录错误5次，进行拦截 一个小时后才可以继续登录
            if(getRedisLoginIntercept(SuccessStaticState.H5SESSIONUSERIP + details.getRemoteAddress(),1,5,TimeUnit.HOURS)){
                throw new BadCredentialsException("用户名连续5次输入错误,锁定ip一个小时！");
            }
            throw new BadCredentialsException("登录名或密码错误");
        } else {
            //密码校验
            if (!switchAccount(afterUser,presentedPassword)) {
                if(getRedisLoginIntercept(SuccessStaticState.H5SESSIONUSER + details.getRemoteAddress(),1,10,TimeUnit.HOURS)){
                    throw new BadCredentialsException("一小时内输入密码错误达到10次,锁定ip一个小时！");
                }
                if(getRedisLoginIntercept(SuccessStaticState.H5SESSIONUSER + details.getRemoteAddress() + username,5,5,TimeUnit.MINUTES)){
                    throw new BadCredentialsException("连续5次密码输入错误，锁定5分钟！");
                }
                //这里做次数拦截  密码输入错误3次后 5分钟后才可以继续尝试
                throw new BadCredentialsException("登录名或密码错误");
            } else {
                //得到用户的权限 (这里使用岗位id)
                List<AfterPower> authorityList = sysRoleDao.findById(afterUser.getAbilityId());
                List<AfterAbility> afterAbilitieList = sysRoleDao.findAfterAbilityByUserId(afterUser.getId());
                afterAbilitieList.removeIf(n -> afterUser.getAbilityId().equals(n.getId()));
                UserDetails userDeatils = new SysUser(username, afterUser.getUserPassword(), authorityList, afterUser.getId(),sysRoleDao.getAbilityNameById(afterUser.getAbilityId()),afterAbilitieList);
                UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userDeatils, null, userDeatils.getAuthorities());
                result.setDetails(authentication.getDetails());
                return result;
            }
        }
    }

    /**
     * 记录错误次数
     * @param key redis记录的key
     * @param teme 过期时间
     * @param timeUnit 过期时间单位
     */
    private boolean getRedisLoginIntercept(String key,long teme,int maxNumber, TimeUnit timeUnit){
            Integer loginNumber = (Integer) redisTemplate.opsForValue().get(key);
        //这里做ip拦截 登录错误10次，进行拦截 一个小时后才可以继续登录
        if(loginNumber==null){
            redisTemplate.opsForValue().set(key,1,teme, timeUnit);
        }else if(loginNumber>=maxNumber){
            return true;
        }else{
            redisTemplate.opsForValue().set(key,loginNumber+1,teme, timeUnit);
        }
        return false;
    }

    /**
     * 错误次数达到多少进行判断
     * @param key redis存放的key
     * @param maxNumber 最大错误次数
     * @return 超过就返回true
     */
    private boolean isRedisLoginIntercept(String key,int maxNumber){
        Integer loginNumber = (Integer) redisTemplate.opsForValue().get(key);
        if(loginNumber!=null && loginNumber>=maxNumber){
            return true;
        }
        return false;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    /**
     * 切换帐号使用
     * @param afterUser 切换后的帐号
     * @param presentedPassword 当前密码
     * @return 返回是否需要密码
     */
    private boolean switchAccount(AfterUser afterUser,String presentedPassword){
        if(StringUtils.isEmpty(presentedPassword)) {
            Authentication originalUser = SecurityContextHolder.getContext().getAuthentication();
            SysUser user = (SysUser) originalUser.getPrincipal();
            return user.getUserId().equals(afterUser.getId());
        }else {
            return Objects.equals(StringUtil.encryptSha256(presentedPassword), afterUser.getUserPassword());
        }
    }
}