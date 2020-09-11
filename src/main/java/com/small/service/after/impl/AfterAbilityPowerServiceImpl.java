package com.small.service.after.impl;

import com.small.dto.after.AfterAbilityPowerDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterAbilityPower;
import com.small.mapper.after.AfterAbilityPowerMapper;
import com.small.service.after.AfterAbilityPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AfterAbilityPowerServiceImpl implements AfterAbilityPowerService {
    @Resource
    private AfterAbilityPowerMapper afterAbilityPowerMapper;
    @Override
    public JsonResponse updateAfterAbilityPower(AfterAbilityPowerDto afterAbilityPowerDto) {
        AfterAbilityPower afterAbilityPower = new AfterAbilityPower();
        afterAbilityPower.setAfterAbilityId(afterAbilityPowerDto.getAfterAbilityId());
        afterAbilityPowerMapper.delete(afterAbilityPower);
        afterAbilityPowerDto.getAfterPowerIds().forEach(afterPowerId->{
            afterAbilityPower.setAfterPowerId(afterPowerId);
            afterAbilityPowerMapper.insert(afterAbilityPower);
            }
        );
        return JsonResponse.ok("岗位权限变更成功");
    }

    @Override
    public List<Integer> getAfterAbilityPowerIdsByAbilityId(Integer afterAbilityId) {
        AfterAbilityPower afterAbilityPower = new AfterAbilityPower();
        afterAbilityPower.setAfterAbilityId(afterAbilityId);
        List<AfterAbilityPower> list= afterAbilityPowerMapper.select(afterAbilityPower);
        return list.stream().map(AfterAbilityPower ::getAfterPowerId).collect(Collectors.toList());
    }
}
