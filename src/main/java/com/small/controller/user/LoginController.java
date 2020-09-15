package com.small.controller.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author xueshiqi
 * @since 2020/4/30
 * 首页登录部分使用
 */
@Controller
public class LoginController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        logger.debug("跳转到登录页面");
        return "login";
    }

    /**
     * 跳转到首页，并且得到切换帐号
     */
    @RequestMapping("/admin-index")
    public String index(Model model){
        logger.debug("跳转到首页");
        return "admin-index";
    }
}
