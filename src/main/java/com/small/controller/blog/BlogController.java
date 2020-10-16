package com.small.controller.blog;

import com.small.constant.BlogState;
import com.small.service.blog.BlogNavigationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public BlogNavigationService blogNavigationService;

    @RequestMapping
    public String getBlog(Model model) {
        logger.debug("博客首页");
        //导航部分，在Redis中取出
//        model.addAttribute("blogNavigationList",blogNavigationService.selectByType(BlogState.INDEXNAVIGATION));
//        model.addAttribute("currentPage","首页");

        return "blog/index";
    }

    @RequestMapping("/detailed")
    public String getBlogDetailed(Model model) {
        logger.debug("茶座部分");
        //导航部分，在Redis中取出
//        model.addAttribute("blogNavigationList",blogNavigationService.selectByType(BlogState.INDEXNAVIGATION));
//        model.addAttribute("currentPage","首页");
        return "blog/detailed";
    }

    @RequestMapping("/details/{describe}")
    public String getBlogDetails(@PathVariable String describe, Model model) {
        logger.debug("开始吹牛逼了");
        //导航部分，在Redis中取出
//        model.addAttribute("blogNavigationList",blogNavigationService.selectByType(BlogState.INDEXNAVIGATION));
//        model.addAttribute("describe",describe);
        return "blog/blog-details";
    }

    @RequestMapping("/mynver")
    public String getMynver(Model model) {
        logger.debug("我的女儿页面");
        return "blog/mynver";
    }

}
