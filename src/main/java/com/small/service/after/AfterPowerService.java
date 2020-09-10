package com.small.service.after;

import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterPowerDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterPower;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 功能管理
 */
public interface AfterPowerService {
    PageInfo<AfterPower> getAfterPower(AfterPowerDto afterPowerDto);

    /**
     * 修改url地址和权限名称
     * @param afterPowerDto
     * @return
     */
    JsonResponse updateAfterPower(AfterPowerDto afterPowerDto);
}
