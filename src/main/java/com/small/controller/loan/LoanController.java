package com.small.controller.loan;

import com.small.dto.loan.LoanCustomerDto;
import com.small.dto.loan.LoanDto;
import com.small.entity.JsonResponse;
import com.small.service.loan.FollowUpCommentService;
import com.small.service.loan.LoanService;
import com.small.service.loan.OutVisitRecordService;
import com.small.service.loan.PhoneUrgeRecordService;
import com.small.utils.OrgUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;

/**
 * @author xueshiqi
 * @since 2020/5/8
 * 贷后管理
 */
@Controller
@RequestMapping("/loan")
public class LoanController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private LoanService loanService;
    @Autowired
    private PhoneUrgeRecordService phoneUrgeRecordService;
    @Autowired
    private OutVisitRecordService outVisitRecordService;
    @Autowired
    private FollowUpCommentService followUpCommentService;

    /**
     * 总部贷后管理
     */
    @RequestMapping("/headLoanManage")
    public String getHeadLoanManage(@Valid LoanDto loanDto, BindingResult result, Model model){
        //根据客户姓名和所属分行查询对应的数据
        logger.debug("传入参数：{}",loanDto);
        // 现在表示执行的验证出现错误
        if (OrgUtil.getValidMessage(result,model)) {
            model.addAttribute("pageContent",loanService.findByHeadLoan(loanDto));
        }
        //回显分行使用
        model.addAttribute("orgIds",loanDto.getOrgIds());
        //公用参数，用于传入是那个模块点击的
        OrgUtil.getModel(model);
        return "loan/headLoanManage";
    }

    /**
     * 分行贷后
     */
    @RequestMapping("/branchLoanManage")
    public String getBranchLoanManage(@Valid LoanDto loanDto, BindingResult result, Model model){
        //根据客户姓名和所属分行查询对应的数据
        logger.debug("传入参数：{}",loanDto);
        if (OrgUtil.getValidMessage(result,model)) {
            model.addAttribute("pageContent",loanService.findByBranchLoan(loanDto));
        }
        //回显分行使用
        model.addAttribute("orgIds",loanDto.getOrgIds());
        //公用参数，用于传入是那个模块点击的
        OrgUtil.getModel(model);
        return "loan/branchLoanManage";
    }

    /**
     * 回收贷后
     */
    @RequestMapping("/recycleLoanManage")
    public String getRecycleLoanManage(@Valid LoanDto loanDto, BindingResult result, Model model){
        //根据客户姓名和所属分行查询对应的数据
        logger.debug("传入参数：{}",loanDto);
        if (OrgUtil.getValidMessage(result,model)) {
            model.addAttribute("pageContent",loanService.findByRecycleLoan(loanDto));
        }
        //回显分行使用
        model.addAttribute("orgIds",loanDto.getOrgIds());
        //公用参数，用于传入是那个模块点击的
        OrgUtil.getModel(model);
        return "loan/recycleLoanManage";
    }

    /**
     * 跟进客户
     */
    @RequestMapping("/loanCustomerDetails/{loanType}")
    public String getLoanCustomerDetails(@PathVariable String loanType, Long projectId,String typeContent, Model model){
        //根据客户姓名和所属分行查询对应的数据
        logger.debug("传入参数：{}",projectId);
        if(projectId == null){
            model.addAttribute("warn","参数异常，请联系管理员！");
        }else {
            model.addAttribute("projectId", projectId);
            model.addAttribute("loanType", loanType);
            model.addAttribute("typeContent", typeContent);
            //客户基本信息
            model.addAttribute("loanCustomerDetails", loanService.findByLoanCustomerDetails(projectId));
            //电催记录
            model.addAttribute("personPhonecheckList", phoneUrgeRecordService.findByPersonPhonecheckList(projectId));
            //外访地址
            model.addAttribute("outVisitRecordList", outVisitRecordService.findByOutVisitRecordList(projectId));
            model.addAttribute("followUpCommentList", followUpCommentService.findByFollowUpCommentList(projectId));
        }
        //公用参数，用于传入是那个模块点击的
        OrgUtil.getModel(model);
        return "loan/loanCustomerDetails";
    }

    /**
     * 保存跟进客户信息
     * @param loanCustomerDto
     */
    @PostMapping("/saveLoanCustomer/{loanType}")
    @ResponseBody
    public JsonResponse saveLoanCustomer(@Validated LoanCustomerDto loanCustomerDto){
        logger.debug("传入参数：{}",loanCustomerDto);
        loanService.saveLoanCustomer(loanCustomerDto);
        return JsonResponse.ok("保存成功");
    }

    /**
     * 保存跟进客户信息
     */
    @PostMapping("/addFollowUpComment/{loanType}")
    @ResponseBody
    public JsonResponse addFollowUpComment(Long projectId,String contactContent){
        return followUpCommentService.addFollowUpComment(projectId,contactContent);
    }
}
