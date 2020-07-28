package com.small.security.service;

import com.small.constant.SuccessStaticState;
import com.small.constant.ToConfigure;
import com.small.entity.after.AfterPower;
import com.small.mapper.after.AfterPowerMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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

    @Resource
    private AfterPowerMapper afterPowerMapper;
    @Autowired
    private ToConfigure toConfigure;

    /**
     * 根据当前访问的url来判断是否有权限进行访问
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        logger.debug("根据url判断是否有权限进行访问");
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        logger.debug("访问的url：{}",requestUrl);
        String authority = getUrlMap(requestUrl.split("\\?")[0]);
        if(StringUtils.isEmpty(authority)){
            logger.warn("当前访问的url：{} 未配置权限，请尽快配置",requestUrl);
            if(toConfigure.getAuthorityVerification().equals("false")) {
                authority = SuccessStaticState.AUTHORITY;
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
            List<AfterPower> list = afterPowerMapper.selectAll();
            if(!CollectionUtils.isEmpty(list)) {
                urlMap = list.stream().collect(Collectors.toMap(AfterPower::getPowerUrl, AfterPower::getAuthority));
            }else {
                urlMap = new HashMap<>();
            }
        }
        String alias = urlMap.get(key);
        if(StringUtils.isEmpty(alias)){
            AfterPower afterPower = new AfterPower();
            afterPower.setPowerUrl(key);
            AfterPower afterPower1 = afterPowerMapper.selectOne(afterPower);
            if(afterPower1 == null) {
                afterPower.setAuthority("");
                afterPower.setPowerAlias("");
                afterPower.setPowerLevel(0);
                afterPowerMapper.insert(afterPower);
                urlMap.put(key,"");
            }else if(!StringUtils.isEmpty(afterPower1.getAuthority())){
                urlMap.put(key,afterPower1.getAuthority());
            }
            return "";
        }else{
            return alias;
        }
    }
}
