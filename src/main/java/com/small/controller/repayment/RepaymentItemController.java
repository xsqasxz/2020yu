package com.small.controller.repayment;

import com.small.constant.UtilsConstant;
import com.small.dto.repayment.RemindDto;
import com.small.service.repayment.RemindService;
import com.small.utils.OrgUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xueshiqi
 * @since 2020/6/1
 * 客户还款计划
 */
@Controller
@RequestMapping("/ri")
public class RepaymentItemController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private RemindService remindService;

    /**
     * 查询还款提醒
     * @param remindDto 还款提醒
     * @param result 校验不符合规则后抛出
     * @param model 传入前台信息
     * @return 返回页面
     */
    @RequestMapping("/remind")
    public String getRemind(@Validated RemindDto remindDto, BindingResult result, Model model){
        //根据客户姓名和所属分行查询对应的数据
        logger.debug("传入参数：{}",remindDto);
        //回显分行使用
        model.addAttribute("orgIds",remindDto.getOrgIds());
        model.addAttribute("remindDto",remindDto);
        // 现在表示执行的验证出现错误
        if (OrgUtil.getValidMessage(result,model)) {
            model.addAttribute("pageContent",remindService.getRemind(remindDto));
        }
        //公用参数，用于传入是那个模块点击的
        OrgUtil.getModel(model,UtilsConstant.LOAN);
        return "repayment/remind";
    }

}
