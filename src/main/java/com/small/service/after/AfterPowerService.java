package com.small.service.after;

import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterPowerDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterPower;
import com.small.vo.after.AfterPowerVo;

import java.util.List;

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

    /**
     * 得到全部岗位，并将传入id的岗位设为选中
     * @param afterAbilityId
     * @return
     */
    List<AfterPowerVo> allAfterPower(Integer afterAbilityId);
}

