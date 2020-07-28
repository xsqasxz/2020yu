package com.small.security.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xueshiqi
 * @since 2020/4/30
 * 认证成功的处理
 */
@Service
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.debug("登录成功");
        httpServletResponse.sendRedirect("/index");
    }
}
