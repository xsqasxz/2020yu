package com.small.service.after.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterPowerDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterPower;
import com.small.mapper.after.AfterPowerMapper;
import com.small.service.after.AfterPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 功能管理
 */
@Service
public class AfterPowerServiceImpl implements AfterPowerService {
    @Resource
    private AfterPowerMapper afterPowerMapper;
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
        afterPowerMapper.updateByPrimaryKeySelective(afterAbility);
        return JsonResponse.ok("修改成功！");
    }


}
