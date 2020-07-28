package com.small.security.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xueshiqi
 * @since 2020/4/30
 * 认证失败的处理
 */
@Service
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.debug("认证失败");
        logger.debug(exception.toString());
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
            request.setAttribute("warn",exception.getMessage());
        } else if (exception instanceof LockedException) {
            request.setAttribute("warn","账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            request.setAttribute("warn","密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            request.setAttribute("warn","账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            request.setAttribute("warn","账户被禁用，请联系管理员!");
        } else {
            request.setAttribute("warn","登录失败!");
        }
        request.getRequestDispatcher("/toLogin").forward(request,response);
    }
}
