package com.small.service.after.impl;

import com.small.dto.after.AfterUserAbilityPowerDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterUserAbility;
import com.small.mapper.after.AfterUserAbilityMapper;
import com.small.service.after.AfterUserAbilityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AfterUserAbilityServiceImpl implements AfterUserAbilityService {

    @Resource
    private AfterUserAbilityMapper afterUserAbilityMapper;
    @Override
    public JsonResponse updateAfterUserAbilityPower(AfterUserAbilityPowerDto afterUserAbilityPowerDto) {
        AfterUserAbility afterUserAbility = new AfterUserAbility();
        afterUserAbility.setAfterUserId(afterUserAbilityPowerDto.getAfterUserId());
        afterUserAbilityMapper.delete(afterUserAbility);
        afterUserAbilityPowerDto.getAfterAbilityIds().forEach(afterAbilityId->{
                afterUserAbility.setAfterAbilityId(afterAbilityId);
                afterUserAbilityMapper.insert(afterUserAbility);
            }
        );
        return JsonResponse.ok("岗位更新成功");
    }
}
