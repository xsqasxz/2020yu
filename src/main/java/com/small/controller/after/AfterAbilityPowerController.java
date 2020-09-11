package com.small.controller.after;

import com.small.dto.after.AfterAbilityPowerDto;
import com.small.entity.JsonResponse;
import com.small.service.after.AfterAbilityPowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 权限管理部分
 */
@Controller
@RequestMapping("/after")
public class AfterAbilityPowerController {
    private final static Logger logger=LoggerFactory.getLogger(Class.class);
    @Autowired
    private AfterAbilityPowerService afterAbilityPowerService;

    /**
     * 帐号权限变更
     * @param afterAbilityPowerDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateAfterAbilityPower")
    public JsonResponse updateAfterAbilityPower(@Valid AfterAbilityPowerDto afterAbilityPowerDto){
        logger.debug("帐号权限变更：{}", afterAbilityPowerDto);
        return afterAbilityPowerService.updateAfterAbilityPower(afterAbilityPowerDto);
    }
}
