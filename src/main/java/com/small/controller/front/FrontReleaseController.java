package com.small.controller.front;

import com.small.constant.FrontState;
import com.small.constant.UtilsConstant;
import com.small.dto.front.FrontReleaseDto;
import com.small.entity.JsonResponse;
import com.small.entity.front.FrontHtml;
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
    private FrontReleaseService frontReleaseService;

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
        FrontHtml frontHtml = frontReleaseService.selectByPrimaryKey(frontReleaseDto);
        model.addAttribute("frontHtml",frontHtml);
        OrgUtil.getModel(model,UtilsConstant.FRONT);
        if(FrontState.HTML_TYPE_DETAILS.equals(frontHtml.getHtmlType())){
            return "blog/blog-details";
        }else{
            return "front/indexTemplate";
        }
    }


    /**
     * 更新首页
     * @return
     */
    @ResponseBody
    @RequestMapping("/crdateHomeHtml")
    public JsonResponse crdateHomeHtml(){
        return frontReleaseService.crdateHomeHtml();
    }

    /**
     * 更新详情列表页面
     */
    @ResponseBody
    @RequestMapping("/crdateDetailedHtml")
    public JsonResponse crdateDetailedHtml(){
        return frontReleaseService.crdateDetailedHtml();
    }

    /**
     * 更新详情页面
     */
    @ResponseBody
    @RequestMapping("/crdatedetailsHtml")
    public JsonResponse crdatedetailsHtml(){
        return frontReleaseService.crdatedetailsHtml();
    }

    /**
     * 返回详情上一页id
     */
    @ResponseBody
    @RequestMapping("/paginationPrev")
    public JsonResponse paginationPrev(Integer frontHtmlId){
        return frontReleaseService.selectIdByPrimaryKey(frontHtmlId,"prev");
    }
    /**
     * 返回详情下一页id
     */
    @ResponseBody
    @RequestMapping("/paginationNext")
    public JsonResponse paginationNext(Integer frontHtmlId){
        return frontReleaseService.selectIdByPrimaryKey(frontHtmlId,"next");
    }

}