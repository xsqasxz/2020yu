package com.small.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.small.constant.SuccessStaticState;
import com.small.entity.JsonResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xueshiqi
 * @since 2020/4/30
 * 此处我们可以自定义403响应的内容,让他返回我们的错误逻辑提示
 */
@Service
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        logger.debug("权限不足，请联系管理员!");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(JsonResponse.error(SuccessStaticState.INSUFFICIENT_AUTHORITY)));
        out.flush();
        out.close();
    }
}
