package com.small.service.after;

import com.small.dto.after.AfterUserAbilityPowerDto;
import com.small.entity.JsonResponse;

import java.util.List;

public interface AfterUserAbilityService {
    JsonResponse updateAfterUserAbilityPower(AfterUserAbilityPowerDto afterUserAbilityPowerDto);

    List<Integer> getAfterAbilityIdsByUserId(Integer afterUserId);
}
