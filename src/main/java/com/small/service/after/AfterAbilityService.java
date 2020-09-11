package com.small.service.after;

import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterAbilityDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterAbility;
import com.small.vo.after.AfterAbilityVo;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 岗位管理
 */
public interface AfterAbilityService {
    PageInfo<AfterAbility> getAfterAbility(AfterAbilityDto afterAbilityDto);

    /**
     * 添加岗位
     * @param afterAbilityDto
     * @return
     */
    JsonResponse addAfterAbility(AfterAbilityDto afterAbilityDto);

    /**
     * 查询全部的岗位
     * @return
     */
    List<AfterAbilityVo> allAfterAbility(Integer afterUserId);

    /**
     * 修改岗位名称
     * @param afterAbilityDto
     * @return
     */
    JsonResponse updateAfterAbilityNameById(AfterAbilityDto afterAbilityDto);
}
