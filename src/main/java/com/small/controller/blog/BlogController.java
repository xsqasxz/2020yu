package com.small.controller.blog;

import com.small.dto.blog.BlogDto;
import com.small.dto.front.FrontReleaseDto;
import com.small.service.blog.BlogNavigationService;
import com.small.service.front.FrontReleaseService;
import com.small.utils.OrgUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public BlogNavigationService blogNavigationService;
    @Autowired
    public FrontReleaseService frontReleaseService;

    @RequestMapping
    public String getBlog(Model model) {
        logger.debug("博客首页");
        //导航部分，在Redis中取出
//        model.addAttribute("blogNavigationList",blogNavigationService.selectByType(BlogState.INDEXNAVIGATION));
//        model.addAttribute("currentPage","首页");

        return "blog/index";
    }

    /**
     * 标题部分
     * @param blogDto
     * @param result
     * @param model
     * @return
     */
    @RequestMapping("/detailed")
    public String getBlogDetailed(@Valid BlogDto blogDto, BindingResult result, Model model) {
        logger.debug("茶座部分");
        // 现在表示执行的验证出现错误
        if (OrgUtil.getValidMessage(result,model)) {
            model.addAttribute("pageContent",blogNavigationService.getDetailed(blogDto));
        }
        return "blog/detailed";
    }

    /**
     * 详情页面
     * @param blogDto
     * @param model
     * @return
     */
    @RequestMapping("/details")
    public String getBlogDetails(@Valid BlogDto blogDto, Model model) {
        logger.debug("开始吹牛逼了");
        //导航部分，在Redis中取出
        model.addAttribute("frontHtml",frontReleaseService.selectByPrimaryKey(blogDto));
        return "blog/blog-details";
    }

    @RequestMapping("/mynver")
    public String getMynver(Model model) {
        logger.debug("我的女儿页面");
        return "blog/mynver";
    }

}
