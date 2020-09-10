package com.small.controller.after;

import com.small.constant.UtilsConstant;
import com.small.dto.after.AfterAbilityDto;
import com.small.entity.JsonResponse;
import com.small.service.after.AfterAbilityService;
import com.small.utils.OrgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 岗位管理
 */
@Controller
@RequestMapping("/after")
public class AfterAbilityController {
    private final static Logger logger=LoggerFactory.getLogger(Class.class);
    @Autowired
    private AfterAbilityService afterAbilityService;
    @RequestMapping("/afterAbility")
    public String afterAbility(@Valid AfterAbilityDto afterAbilityDto, BindingResult result, Model model) {
        //根据客户姓名和所属分行查询对应的数据
        logger.debug("传入参数：{}", afterAbilityDto);
        //用于回显查询条件
        model.addAttribute("afterAbilityDto", afterAbilityDto);
        // 现在表示执行的验证出现错误
        if (OrgUtil.getValidMessage(result,model)) {
            model.addAttribute("pageContent",afterAbilityService.getAfterAbility(afterAbilityDto));
        }
        //公用参数，用于传入是那个模块点击的
        OrgUtil.getModel(model,UtilsConstant.AFTER);
        return "after/afterAbility";
    }

    /**
     * 添加岗位
     * @return
     */
    @ResponseBody
    @RequestMapping("/addAfterAbility")
    public JsonResponse addAfterAbility(@Valid AfterAbilityDto afterAbilityDto){
        logger.debug("添加岗位传入参数：{}", afterAbilityDto);
        return afterAbilityService.addAfterAbility(afterAbilityDto);
    }
}
