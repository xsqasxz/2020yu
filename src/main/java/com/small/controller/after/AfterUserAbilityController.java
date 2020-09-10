package com.small.controller.after;

import com.small.dto.after.AfterUserAbilityPowerDto;
import com.small.entity.JsonResponse;
import com.small.service.after.AfterUserAbilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/after")
public class AfterUserAbilityController {
    private final static Logger logger=LoggerFactory.getLogger(Class.class);
    @Autowired
    private AfterUserAbilityService afterUserAbilityService;
    @ResponseBody
    @RequestMapping("/updateAfterUserAbilityPower")
    public JsonResponse updateAfterUserAbilityPower(@Valid AfterUserAbilityPowerDto afterUserAbilityPowerDto){
        logger.debug("岗位变更：{}", afterUserAbilityPowerDto);
        return afterUserAbilityService.updateAfterUserAbilityPower(afterUserAbilityPowerDto);
    }
}
