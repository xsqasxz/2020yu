package com.small.controller.blog;

import com.small.constant.BlogState;
import com.small.service.blog.NavigationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xueshiqi
 * @since 2020/9/15
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    public NavigationService navigationService;

    @RequestMapping
    public String getBlog(Model model) {
        logger.debug("博客首页");
        //导航部分，在Redis中取出
        model.addAttribute("navigation",navigationService.selectByType(BlogState.INDEXNAVIGATION));
        //

        return "blog/index";
    }

    @RequestMapping("/mynver")
    public String getMynver(Model model) {
        logger.debug("我的女儿页面");
        return "blog/mynver";
    }

}
