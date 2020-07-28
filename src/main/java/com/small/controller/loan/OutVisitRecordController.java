package com.small.controller.loan;

import com.small.dto.loan.OutVisitRecordDto;
import com.small.entity.JsonResponse;
import com.small.service.loan.OutVisitRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xueshiqi
 * @since 2020/5/26
 * 外访地址
 */
@Controller
@RequestMapping("/ovr")
public class OutVisitRecordController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private OutVisitRecordService outVisitRecordService;

    /**
     * 编辑外访地址
     * @param loanType
     * @param outVisitRecordDto
     * @param model
     * @return
     */
    @RequestMapping("/editOutVisitRecord/{loanType}")
    public String editOutVisitRecord(@PathVariable String loanType, OutVisitRecordDto outVisitRecordDto, Model model){
        logger.debug("传入参数 id：{}", outVisitRecordDto);
        model.addAttribute("loanType", loanType);
        model.addAttribute("outVisitRecord", outVisitRecordService.findByOutVisitRecord(outVisitRecordDto));
        model.addAttribute("pageContent",outVisitRecordService.findByOutVisitDetailPage(outVisitRecordDto));
        return "loan/editOutVisitRecord";
    }

    /**
     * 保存外访地址
     * @param outVisitRecordDto
     * @return
     */
    @RequestMapping("/saveOutVisitRecord/{loanType}")
    @ResponseBody
    public JsonResponse saveOutVisitRecord(@Validated OutVisitRecordDto outVisitRecordDto){
        return outVisitRecordService.saveOutVisitRecord(outVisitRecordDto);
    }

    /**
     * 添加外访评论
     * @param outVisitRecordId
     * @param projectId
     * @param contactContent
     * @return
     */
    @RequestMapping("/addOutVisitDetail/{loanType}")
    @ResponseBody
    public JsonResponse addOutVisitDetail(Long outVisitRecordId, Long projectId, String contactContent){
        return outVisitRecordService.addOutVisitDetail(outVisitRecordId,projectId,contactContent);
    }
    /**
     * 查看外访全部地址
     * @param loanType
     * @param model
     * @return
     */
    @RequestMapping("/allOutVisitRecord/{loanType}")
    public String allOutVisitRecord(@PathVariable String loanType,Long projectId, Model model){
        logger.debug("传入参数 id：{}", projectId);
        model.addAttribute("loanType", loanType);
        model.addAttribute("projectId", projectId);
        model.addAttribute("outVisitRecordList",outVisitRecordService.allOutVisitRecord(projectId));
        return "loan/allOutVisitRecord";
    }
}
