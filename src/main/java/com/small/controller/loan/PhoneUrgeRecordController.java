package com.small.controller.loan;

import com.small.dto.loan.PersonPhonecheckDto;
import com.small.entity.JsonResponse;
import com.small.service.loan.PhoneUrgeRecordService;
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
 * 电催记录
 */
@Controller
@RequestMapping("/pur")
public class PhoneUrgeRecordController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private PhoneUrgeRecordService phoneUrgeRecordService;
    /**
     * 编辑电催记录
     * @param model
     */
    @RequestMapping("/editPersonPhonecheck/{loanType}")
    public String editPersonPhonecheck(@PathVariable String loanType,PersonPhonecheckDto personPhonecheckDto, Model model){
        logger.debug("传入参数 id：{}", personPhonecheckDto);
        model.addAttribute("loanType", loanType);
        model.addAttribute("personPhonecheck", phoneUrgeRecordService.findByPersonPhonecheck(personPhonecheckDto));
        model.addAttribute("pageContent",phoneUrgeRecordService.findByPhoneUrgeRecordPage(personPhonecheckDto));
        return "loan/editPersonPhonecheck";
    }

    /**
     * 添加电催评论
     * @param personPhonecheckId
     * @param projectid
     * @param contactContent
     * @return
     */
    @RequestMapping("/addPhoneUrgeRecord/{loanType}")
    @ResponseBody
    public JsonResponse addPhoneUrgeRecord(Long personPhonecheckId,Long projectid,String contactContent){
        return phoneUrgeRecordService.addPhoneUrgeRecord(personPhonecheckId,projectid,contactContent);
    }

    /**
     * 查看全部电催记录
     * @param model
     * @return
     */
    @RequestMapping("/allPhoneUrgeRecord/{loanType}")
    public String allPhoneUrgeRecord(@PathVariable String loanType,Long projectid,Model model){
        model.addAttribute("loanType", loanType);
        model.addAttribute("projectid", projectid);
        model.addAttribute("personPhonecheckList",phoneUrgeRecordService.allPhoneUrgeRecord(projectid));
        return "loan/allPhoneUrgeRecord";
    }
}
