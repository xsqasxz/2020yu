package com.small.controller.blog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author xueshiqi
 * @since 2020/9/15
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    private static final Logger logger = LogManager.getLogger();

    @RequestMapping
    public String getBlog(Model model) {

        return "/blog/index";
    }

    @RequestMapping("/mynver")
    public String getMynver(Model model) {
        return "/blog/mynver";
    }

}
