package com.small.service.after;

import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterAbilityDto;
import com.small.entity.after.AfterAbility;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 岗位管理
 */
public interface AfterAbilityService {
    PageInfo<AfterAbility> getAfterAbility(AfterAbilityDto afterAbilityDto);
}
