package com.small.controller;

import com.small.entity.MyTest;
import com.small.service.MyTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Optional;

/**
 * @author xueshiqi
 * @since 2020/4/28
 */
@Controller
public class DemoController {
    private final static Logger logger=LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private MyTestService myTestService;

    @ResponseBody
    @GetMapping("/hello")
    public String getHello(){
        return "你好！";
    }

    @ResponseBody
    @GetMapping("/getUserById")
    public MyTest getUserById(Long id){
        MyTest myTest = myTestService.findById(id);
        return myTest;
    }

    @RequestMapping("/test")
    public String test(Model model) {
        List<MyTest> list = myTestService.findAll();
        Optional<MyTest> optionalMyTest =  list.stream().findAny();
        optionalMyTest.ifPresent(myTest -> model.addAttribute("user", myTest));
        return "index";
    }

    @RequestMapping("/demo")
    public String demo(Model model) {
        return "demo";
    }

    @RequestMapping("/demoList")
    public String demoList(Model model) {
        return "demoList";
    }

    @RequestMapping("/underMaintenance")
    public String underMaintenance(Model model){
        model.addAttribute("warn","功能维护中，敬请期待！");
        return "index";
    }

}
