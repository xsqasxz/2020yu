package com.small.service.after.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.constant.RedisNameState;
import com.small.dto.after.AfterPowerDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterPower;
import com.small.mapper.after.AfterPowerMapper;
import com.small.service.after.AfterAbilityPowerService;
import com.small.service.after.AfterPowerService;
import com.small.vo.after.AfterPowerVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 功能管理
 */
@Service
public class AfterPowerServiceImpl implements AfterPowerService {
    @Resource
    private AfterPowerMapper afterPowerMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AfterAbilityPowerService afterAbilityPowerService;
    @Override
    public PageInfo<AfterPower> getAfterPower(AfterPowerDto afterPowerDto) {
        AfterPowerDto.getAfterPowerDto(afterPowerDto);
        PageHelper.startPage(afterPowerDto.getPageNum(), afterPowerDto.getPageSize());
        return new PageInfo<>(afterPowerMapper.getAfterPower(afterPowerDto));
    }

    @Override
    public JsonResponse updateAfterPower(AfterPowerDto afterPowerDto) {
        AfterPower afterAbility = new AfterPower();
        afterAbility.setId(afterPowerDto.getAfterPowerId());
        afterAbility.setPowerUrl(afterPowerDto.getPowerUrl());
        afterAbility.setAuthority(afterPowerDto.getAuthority());
        afterAbility.setPowerAlias(afterPowerDto.getPowerAlias());
        afterPowerMapper.updateByPrimaryKeySelective(afterAbility);
        //清空缓存
        redisTemplate.delete(RedisNameState.afterPowerList);
        return JsonResponse.ok("修改成功！");
    }

    @Override
    public List<AfterPowerVo> allAfterPower(Integer afterAbilityId) {
        List<AfterPower> afterPowerAllList = redisTemplate.opsForList().range(RedisNameState.afterPowerList, 0, -1);
        if (CollectionUtils.isEmpty(afterPowerAllList)) {
            afterPowerAllList = afterPowerMapper.selectAll();
            redisTemplate.opsForList().rightPushAll(RedisNameState.afterPowerList, afterPowerAllList);
        }
        List<Integer> list = afterAbilityPowerService.getAfterAbilityPowerIdsByAbilityId(afterAbilityId);
        List<AfterPowerVo> afterPowerVos = new ArrayList<>();
        afterPowerAllList.forEach(afterPower -> {
            if(StringUtils.isNotEmpty(afterPower.getPowerAlias())) {
                AfterPowerVo afterAbilityVo = new AfterPowerVo(afterPower);
                afterAbilityVo.setAbilityChecked(list.contains(afterPower.getId()));
                afterPowerVos.add(afterAbilityVo);
            }
        });
        return afterPowerVos;
    }
}
