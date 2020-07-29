package com.small.mapper.after;

import com.small.dto.after.AfterPowerDto;
import com.small.entity.after.AfterPower;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/7/27
 */
public interface AfterPowerMapper extends Mapper<AfterPower> {
    List<AfterPower> selectAfterPowerByAbilityId(Integer abilityId);

    List<AfterPower> getAfterPower(AfterPowerDto afterPowerDto);
}
