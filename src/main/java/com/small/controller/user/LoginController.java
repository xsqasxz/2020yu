package com.small.controller.user;

//import com.small.service.user.NewPositionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author xueshiqi
 * @since 2020/4/30
 * 首页登录部分使用
 */
@Controller
public class LoginController {

    private static final Logger logger = LogManager.getLogger();

//    @Autowired
//    private NewPositionService newPositionService;

    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        logger.debug("跳转到登录页面");
        return "login";
    }

    /**
     * 跳转到首页，并且得到切换帐号
     */
    @RequestMapping("/index")
    public String index(Model model){
        logger.debug("跳转到首页");
        //跟进当前登录用户查询出该用户下面的全部身份
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SysUser user = (SysUser)authentication.getPrincipal();
//        List<NewPosition> newPositionList = newPositionService.findByAccountName(user.getUsername());
//        newPositionList.removeIf(newPosition -> user.getNewPositionName().equals(newPosition.getName()));
//        model.addAttribute("newPositionList",newPositionList);
        return "index";
    }
}
