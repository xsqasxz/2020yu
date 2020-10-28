package com.small.controller.front;

import com.small.constant.UtilsConstant;
import com.small.dto.front.FrontReleaseDto;
import com.small.entity.JsonResponse;
import com.small.service.front.FrontReleaseService;
import com.small.utils.OrgUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author xueshiqi
 * @since 2020/10/13
 * 文章发布
 */
@Controller
@RequestMapping("/front")
public class FrontReleaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    public FrontReleaseService frontReleaseService;

    @RequestMapping("/frontRelease")
    public String frontRelease(@Valid FrontReleaseDto frontReleaseDto,Model model) {
        logger.debug("发布文章");
        model.addAttribute("frontHtml",frontReleaseService.selectByPrimaryKey(frontReleaseDto));
        OrgUtil.getModel(model,UtilsConstant.FRONT);
        return "front/frontRelease";
    }

    /**
     * 保存模版
     * @param frontReleaseDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveFrontRelease")
    public JsonResponse saveFrontRelease(@Valid FrontReleaseDto frontReleaseDto){
        logger.debug("保存模版");
        return frontReleaseService.saveFrontRelease(frontReleaseDto);
    }

    /**
     * 删除模版
     * @param frontReleaseDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteFrontHtml")
    public JsonResponse deleteFrontHtml(@Valid FrontReleaseDto frontReleaseDto){
        logger.debug("删除模版");
        return frontReleaseService.deleteFrontHtml(frontReleaseDto);
    }

    /**模版预览*/
    @RequestMapping("/preview")
    public String preview(@Valid FrontReleaseDto frontReleaseDto,Model model) {
        logger.debug("模版预览:{}"+frontReleaseDto);
        model.addAttribute("frontHtml",frontReleaseService.selectByPrimaryKey(frontReleaseDto));
        OrgUtil.getModel(model,UtilsConstant.FRONT);
        return "front/indexTemplate";
    }
}