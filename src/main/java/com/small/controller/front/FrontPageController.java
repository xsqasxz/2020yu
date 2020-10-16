package com.small.controller.front;

import com.small.constant.UtilsConstant;
import com.small.dto.front.FrontPageDto;
import com.small.service.front.FrontPageService;
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
 * @since 2020/10/13
 * 静态页面管理
 */
@Controller
@RequestMapping("/front")
public class FrontPageController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    public FrontPageService frontPageService;

    @RequestMapping("/frontPage")
    public String frontRelease(@Valid FrontPageDto frontPageDto, BindingResult result, Model model) {
        logger.debug("页面管理");
        //根据客户姓名和所属分行查询对应的数据
        logger.debug("传入参数：{}", frontPageDto);
        //用于回显查询条件
        model.addAttribute("frontPageDto", frontPageDto);
        // 现在表示执行的验证出现错误
        if (OrgUtil.getValidMessage(result,model)) {
            model.addAttribute("pageContent",frontPageService.getFrontHtml(frontPageDto));
        }
        OrgUtil.getModel(model,UtilsConstant.FRONT);
        return "front/frontPage";
    }

}
