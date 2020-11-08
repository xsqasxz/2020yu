package com.small.utils.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandle {
    private static final Logger logger = LogManager.getLogger();
    //捕获全局异常，处理所有不可知的异常 这个注解是捕获所有异常
    @ExceptionHandler(value=Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {
        //此处返回json数据
        logger.error("msg:{},url:{}", e.getMessage(), request.getRequestURL());
        Map<String, Object> map = new HashMap<>();
        //捕捉到的异常如果是自定义异常类，那么就返回自定义异常类中的错误码和错误信息
        if(e instanceof MyException){
            map.put("code",((MyException) e).getCode());
            map.put("msg",((MyException) e).getMsg());
        }
        //这里是除了自定义异常的其他异常信息
        else {
            map.put("code", 100);
            map.put("msg", e.getMessage());
        }
        map.put("url", request.getRequestURL());
        return map;

        //此处是进行页面跳转
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("404.html");
//        modelAndView.addObject("msg", e.getMessage());
//        return modelAndView;
    }
//    //这个注解是表示 只捕获自定义的异常类
//    @ExceptionHandler(MyException.class)
//    public Object handleException(Exception e, HttpServletRequest request) {
//        //此处返回json数据
//        LOG.error("msg:{},url:{}", e.getMessage(), request.getRequestURL());
//        Map<String, Object> map = new HashMap<>();
//        map.put("code",((MyException) e).getCode());
//        map.put("msg",((MyException) e).getMsg());
//        map.put("url", request.getRequestURL());
//        return map;
//
//    }
//}
}
