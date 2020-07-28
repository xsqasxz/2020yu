package com.small.security.service;

import com.small.constant.SuccessStaticState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xueshiqi
 * @since 2020/4/24
 * Decision决定的意思。
 * 有了权限资源(MyFilterInvocationSecurityMetadataSource)，知道了当前访问的url需要的具体权限，接下来就是决策当前的访问是否能通过权限验证了
 * MyAccessDecisionManager 自定义权限决策管理器
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {
    private static final Logger logger = LogManager.getLogger();
    /**
     * 取当前用户的权限与这次请求的这个url需要的权限作对比，决定是否放行
     * @param authentication 包含了当前的用户信息，包括拥有的权限,即之前UserDetailsService登录时候存储的用户对象
     * @param o 就是FilterInvocation对象，可以得到request等web资源
     * @param collection 是本次访问需要的权限。即上一步的 MyFilterInvocationSecurityMetadataSource 中查询核对得到的权限列表
     * @throws AccessDeniedException 返回没有权限
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        logger.debug("进行权限校验");
        AtomicBoolean isJurisdiction = new AtomicBoolean(true);
        if(!CollectionUtils.isEmpty(collection)){
            collection.forEach(configAttribute -> {
                String needRole = configAttribute.getAttribute();
                logger.debug("权限名称:{} ,登录帐号名称：{}",needRole,authentication.getName());
                if(SuccessStaticState.AUTHORITY.equals(needRole) && !SuccessStaticState.ANONYMOUSUSER.equals(authentication.getName())) {
                    isJurisdiction.set(false);
                }else{
                    //admin帐号和登录帐号不进行校验
                    authentication.getAuthorities().forEach(ga -> {
                        if (needRole.equals(ga.getAuthority())) {
                            isJurisdiction.set(false);
                        }
                    });
                }
            });
            if(isJurisdiction.get()) {
                logger.debug("无此权限");
                throw new AccessDeniedException("无此权限");
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        logger.debug("supports");
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        logger.debug("supports");
        return true;
    }
}
