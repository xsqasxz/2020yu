package com.small.service.after.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterAbilityDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterAbility;
import com.small.mapper.after.AfterAbilityMapper;
import com.small.service.after.AfterAbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 岗位管理
 */
@Service
public class AfterAbilityServiceImpl implements AfterAbilityService {
    @Resource
    private AfterAbilityMapper afterAbilityMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<AfterAbility> getAfterAbility(AfterAbilityDto afterAbilityDto) {
        AfterAbilityDto.getAfterAbilityDto(afterAbilityDto);
        PageHelper.startPage(afterAbilityDto.getPageNum(), afterAbilityDto.getPageSize());
        return new PageInfo<>(afterAbilityMapper.getAfterAbility(afterAbilityDto));
    }

    @Override
    public JsonResponse addAfterAbility(AfterAbilityDto afterAbilityDto) {
        AfterAbility afterAbility = new AfterAbility();
        afterAbility.setAbilityName(afterAbilityDto.getAbilityName());
        if(CollectionUtils.isEmpty(afterAbilityMapper.select(afterAbility))){
            afterAbility.setAbilityEffective(false);
            afterAbilityMapper.insert(afterAbility);
            return JsonResponse.ok("添加成功！");
        }else{
            return JsonResponse.error("岗位已存在！");
        }
    }

    @Override
    public List<AfterAbility> allAfterAbility() {
        List<AfterAbility> afterAbilityAllList = redisTemplate.opsForList().range("AfterAbilityAllList", 0, -1);
        if (CollectionUtils.isEmpty(afterAbilityAllList)) {
            afterAbilityAllList = afterAbilityMapper.selectAll();
            redisTemplate.opsForList().rightPushAll("AfterAbilityAllList", afterAbilityAllList);
            return afterAbilityAllList;
        } else {
            return afterAbilityAllList;
        }
    }
}
