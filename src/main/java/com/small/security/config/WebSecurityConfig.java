package com.small.security.config;

import com.small.security.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author xueshiqi
 * @since 2020/3/30
 * spring-security权限管理的核心配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //全局
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    //自定义密码校验
    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;
    //权限过滤器（当前url所需要的访问权限）
    @Autowired
    private MyInvocationSecurityMetadataSourceService myInvocationSecurityMetadataSourceService;
    //权限决策器
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;
    //自定义错误(403)无访问权限 返回数据
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 配置userDetails的数据源，密码加密格式
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.debug("configure");
        auth.authenticationProvider(this.myAuthenticationProvider);
    }

    /**
     * 配置放行的资源
     */
    @Override
    public void configure(WebSecurity web){
        // 给 swagger 放行；不需要权限能访问的资源
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/i/**");
        web.ignoring().antMatchers("/image/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/toLogin");
        web.ignoring().antMatchers("/blog/**");
        web.ignoring().antMatchers("/*.html");
    }

    /**
     * HttpSecurity包含了原数据（主要是url）
     * 通过withObjectPostProcessor将MyFilterInvocationSecurityMetadataSource和MyAccessDecisionManager注入进来
     * 此url先被MyFilterInvocationSecurityMetadataSource处理，然后 丢给 MyAccessDecisionManager处理
     * 如果不匹配，返回 MyAccessDeniedHandler
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(myInvocationSecurityMetadataSourceService);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                /*.formLogin().loginPage("/toLogin").loginProcessingUrl("/login")*/
                .formLogin().loginPage("/index.html").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(new MyAuthenticationFailureHandler())
                .successHandler(new MyAuthenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }
}