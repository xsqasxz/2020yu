package com.small.service.after.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterAbilityDto;
import com.small.entity.after.AfterAbility;
import com.small.mapper.after.AfterAbilityMapper;
import com.small.service.after.AfterAbilityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 岗位管理
 */
@Service
public class AfterAbilityServiceImpl implements AfterAbilityService {
    @Resource
    private AfterAbilityMapper afterAbilityMapper;
    @Override
    public PageInfo<AfterAbility> getAfterAbility(AfterAbilityDto afterAbilityDto) {
        AfterAbilityDto.getAfterAbilityDto(afterAbilityDto);
        PageHelper.startPage(afterAbilityDto.getPageNum(), afterAbilityDto.getPageSize());
        return new PageInfo<>(afterAbilityMapper.getAfterAbility(afterAbilityDto));
    }
}
