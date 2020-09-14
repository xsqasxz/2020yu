package com.small.controller.after;

import com.small.constant.UtilsConstant;
import com.small.dto.after.AfterUserDto;
import com.small.entity.JsonResponse;
import com.small.service.after.AfterUserService;
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
 * @since 2020/7/28
 * 用户帐号管理
 */
@Controller
@RequestMapping("/after")
public class AfterUserController {
    private final static Logger logger=LoggerFactory.getLogger(Class.class);
    @Autowired
    private AfterUserService afterUserService;

    /**
     * 帐号管理界面
     * @param afterUserDto
     * @param result
     * @param model
     * @return
     */
    @RequestMapping("/afterUser")
    public String afterUser(@Valid AfterUserDto afterUserDto, BindingResult result, Model model) {
        //根据客户姓名和所属分行查询对应的数据
        logger.debug("传入参数：{}", afterUserDto);
        //用于回显查询条件
        model.addAttribute("afterUserDto", afterUserDto);
        // 现在表示执行的验证出现错误
        if (OrgUtil.getValidMessage(result,model)) {
            model.addAttribute("pageContent",afterUserService.getAfterUser(afterUserDto));
        }
        //公用参数，用于传入是那个模块点击的
        OrgUtil.getModel(model,UtilsConstant.AFTER);
        return "after/afterUser";
    }

    /**
     * 重置密码
     * @param afterUserDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/resetPassword")
    public JsonResponse resetPassword(@Valid AfterUserDto afterUserDto){
        logger.debug("重置密码传入参数：{}", afterUserDto);
        return afterUserService.resetPassword(afterUserDto);
    }

    /**
     * 添加用户帐号
     * @param afterUserDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/addAfterUser")
    public JsonResponse addAfterUser(@Valid AfterUserDto afterUserDto){
        logger.debug("添加用户传入参数：{}", afterUserDto);
        return afterUserService.addAfterUser(afterUserDto);
    }

    /**
     * 修改用户默认岗位
     * @param afterUserDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateAfterUserAbilityId")
    public JsonResponse updateAfterUserAbilityId(@Valid AfterUserDto afterUserDto){
        logger.debug("修改用户默认岗位传入参数：{}", afterUserDto);
        return afterUserService.updateAfterUserAbilityId(afterUserDto);
    }

}
