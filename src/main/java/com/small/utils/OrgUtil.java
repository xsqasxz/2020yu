package com.small.utils;

import com.github.pagehelper.PageInfo;
import com.small.security.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xueshiqi
 * @since 2020/5/8
 */
public class OrgUtil {

    /**
     * 取的权限交集
     * @param orgIds
     * @return
     */
//    public static List<Long> getOrgIds(List<Long> orgIds){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SysUser user = (SysUser)authentication.getPrincipal();
//        if(CollectionUtils.isEmpty(orgIds)){
//            return user.getOrganizingList().stream().map(Organizing :: getId).collect(Collectors.toList());
//        }else{
//            return user.getOrganizingList().stream().filter(organizing -> orgIds.contains(organizing.getId())).map(Organizing :: getId).collect(Collectors.toList());
//        }
//    }

    /**
     * 有错误返回false
     * @param result 错误信息
     * @param model 返回给前端数据
     * @return
     */
    public static boolean getValidMessage(BindingResult result, Model model){
        if (result.hasErrors()) { // 现在表示执行的验证出现错误
            String message = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining());
            //返回错误数据
            model.addAttribute("warn",message);
            //返回一个空的分页对象
            model.addAttribute("pageContent",new PageInfo<>());
            return false;
        }
        return true;
    }

    /**
     * 公用参数，用于传入是那个模块点击的
     * @param model
     */
    public static void getModel(Model model,String parentNode){
        //公用参数，用于传入是那个模块点击的
        model.addAttribute("parentNode",parentNode);
    }
}
