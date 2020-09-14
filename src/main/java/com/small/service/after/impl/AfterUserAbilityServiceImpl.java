package com.small.service.after.impl;

import com.small.dto.after.AfterUserAbilityPowerDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterUser;
import com.small.entity.after.AfterUserAbility;
import com.small.mapper.after.AfterUserAbilityMapper;
import com.small.mapper.after.AfterUserMapper;
import com.small.service.after.AfterUserAbilityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AfterUserAbilityServiceImpl implements AfterUserAbilityService {

    @Resource
    private AfterUserAbilityMapper afterUserAbilityMapper;
    @Resource
    private AfterUserMapper afterUserMapper;
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
        //修改默认岗位
        AfterUser afterUser = afterUserMapper.selectByPrimaryKey(afterUserAbilityPowerDto.getAfterUserId());
        if(!afterUserAbilityPowerDto.getAfterAbilityIds().contains(afterUser.getAbilityId())){
            afterUser.setAbilityId(afterUserAbilityPowerDto.getAfterAbilityIds().get(0));
            afterUserMapper.updateByPrimaryKeySelective(afterUser);
        }
        return JsonResponse.ok("岗位更新成功");
    }

    @Override
    public List<Integer> getAfterAbilityIdsByUserId(Integer afterUserId) {
        AfterUserAbility afterUserAbility = new AfterUserAbility();
        afterUserAbility.setAfterUserId(afterUserId);
        List<AfterUserAbility> list= afterUserAbilityMapper.select(afterUserAbility);
        return list.stream().map(AfterUserAbility ::getAfterAbilityId).collect(Collectors.toList());
    }
}
