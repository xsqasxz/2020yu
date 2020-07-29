package com.small.controller.after;

import com.small.constant.UtilsConstant;
import com.small.dto.after.AfterPowerDto;
import com.small.service.after.AfterPowerService;
import com.small.utils.OrgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 功能管理
 */
@Controller
@RequestMapping("/after")
public class AfterPowerController {
    private final static Logger logger=LoggerFactory.getLogger(Class.class);
    @Autowired
    private AfterPowerService afterPowerService;
    @RequestMapping("/afterPower")
    public String afterPower(@Valid AfterPowerDto afterPowerDto, BindingResult result, Model model) {
        //根据客户姓名和所属分行查询对应的数据
        logger.debug("传入参数：{}", afterPowerDto);
        //用于回显查询条件
        model.addAttribute("afterPowerDto", afterPowerDto);
        // 现在表示执行的验证出现错误
        if (OrgUtil.getValidMessage(result,model)) {
            model.addAttribute("pageContent",afterPowerService.getAfterPower(afterPowerDto));
        }
        //公用参数，用于传入是那个模块点击的
        OrgUtil.getModel(model,UtilsConstant.AFTER);
        return "after/afterPower";
    }
}
