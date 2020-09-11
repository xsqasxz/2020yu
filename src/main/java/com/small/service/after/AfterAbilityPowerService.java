package com.small.service.after;

import com.small.dto.after.AfterAbilityPowerDto;
import com.small.entity.JsonResponse;

import java.util.List;

public interface AfterAbilityPowerService {
    JsonResponse updateAfterAbilityPower(AfterAbilityPowerDto afterAbilityPowerDto);

    List<Integer> getAfterAbilityPowerIdsByAbilityId(Integer afterAbilityId);
}
