package com.small.security.service;

import com.small.config.BeanFactory;
import com.small.constant.SuccessStaticState;
import com.small.constant.ToConfigure;
import com.small.entity.after.AfterPower;
import com.small.mapper.after.AfterPowerMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xueshiqi
 * @since 2020/4/24
 * FilterInvocationSecurityMetadataSource（权限资源过滤器接口）继承了 SecurityMetadataSource（权限资源接口）
 * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限；Metadata是元数据的意思。
 * 自定义权限资源过滤器，实现动态的权限验证
 * 它的主要责任就是当访问一个url时，返回这个url所需要的访问权限
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    private static final Logger logger = LogManager.getLogger();

    //url别名记录
    private static Map<String,String> urlMap;

    private static Boolean authorityVerification;
    public MyInvocationSecurityMetadataSourceService() {
        if(authorityVerification==null){
            authorityVerification = Boolean.valueOf(BeanFactory.getBean(ToConfigure.class).getAuthorityVerification());
        }
    }



    /**
     * 根据当前访问的url来判断是否有权限进行访问
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//        logger.debug("根据url判断是否有权限进行访问");
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
//        logger.debug("访问的url：{}",requestUrl);
        String key = requestUrl.split("\\?")[0];
        String authority = getUrlMap(key);
        if(StringUtils.isEmpty(authority)){
            logger.warn("当前访问的url：{} 未配置权限，请尽快配置",requestUrl);
            //这里加入一个切面，没有权限的url地址全部记录下来
            if(!authorityVerification) {
                authority = SuccessStaticState.AUTHORITY;
            }else{
                return null;
            }
        }
        return SecurityConfig.createList(authority);
    }

    /**
     * 此处方法如果做了实现，返回了定义的权限资源列表，
     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确，
     * 如果不需要校验，这里实现方法，方法体直接返回null即可
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        logger.debug("getAllConfigAttributes");
        return null;
    }

    /**
     * 方法返回类对象是否支持校验，
     * web项目一般使用FilterInvocation来判断，或者直接返回true
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        logger.debug("supports");
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    private String getUrlMap(String key){
        if(urlMap==null){
            List<AfterPower> list = BeanFactory.getBean(AfterPowerMapper.class).selectAll();
            if(!CollectionUtils.isEmpty(list)) {
                urlMap = list.stream().collect(Collectors.toMap(AfterPower::getPowerUrl, AfterPower::getAuthority));
            }else {
                urlMap = new HashMap<>();
            }
        }
        //这里配置统配*号匹配
        String alias = urlMap.get(key);
        if(alias == null) {
            //如果没找到，就把最后一位替换为*，然后递归进行查找直到全部换为*为止
            String keys = replaceBehind(key);
            if (keys != null) {
                return getUrlMap(replaceBehind(key));
            }
            return null;
        }else{
            return alias;
        }
    }

    /**
     * 更新静态权限
     */
    public void updateUrlMap(){
        List<AfterPower> list = BeanFactory.getBean(AfterPowerMapper.class).selectAll();
        if(!CollectionUtils.isEmpty(list)) {
            urlMap = list.stream().collect(Collectors.toMap(AfterPower::getPowerUrl, AfterPower::getAuthority));
        }else {
            urlMap = new HashMap<>();
        }
    }
    /**
     * 将最后一个/后面的替换为*
     * @param key
     * @return
     */
    private String replaceBehind(String key){
        String[] aggregate = key.split(SuccessStaticState.SLASH);
        StringBuffer stringBuffer = new StringBuffer();
        boolean whetherReplace=true;
        for(int i =aggregate.length-1;i>0;i--){
            String s = aggregate[i];
            if(whetherReplace && !SuccessStaticState.ASTERISK.equals(s)) {
                stringBuffer.insert(0, SuccessStaticState.ASTERISK);
                whetherReplace = false;
            }else{
                stringBuffer.insert(0, s);
            }
            stringBuffer.insert(0,SuccessStaticState.SLASH) ;
        }
        if(whetherReplace){
            return null;
        }else {
            return stringBuffer.toString();
        }
    }
}
