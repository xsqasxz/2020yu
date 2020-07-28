package com.small.security.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xueshiqi
 * @since 2020/4/30
 * 注销
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.debug("注销成功");
        httpServletResponse.sendRedirect("/toLogin");
    }
}
