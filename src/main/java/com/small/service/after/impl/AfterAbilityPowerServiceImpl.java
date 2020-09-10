package com.small.service.after.impl;

import com.small.dto.after.AfterAbilityPowerDto;
import com.small.entity.JsonResponse;
import com.small.mapper.after.AfterAbilityPowerMapper;
import com.small.service.after.AfterAbilityPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AfterAbilityPowerServiceImpl implements AfterAbilityPowerService {
    @Resource
    private AfterAbilityPowerMapper afterAbilityPowerMapper;
    @Override
    public JsonResponse updateAfterAbilityPower(AfterAbilityPowerDto afterAbilityPowerDto) {
//        afterAbilityPowerMapper

        return JsonResponse.ok("岗位变更成功！");
    }
}
