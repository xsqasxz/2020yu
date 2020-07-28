package com.small.mapper.after;

import com.small.entity.after.AfterAbility;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/7/27
 */
public interface AfterAbilityMapper extends Mapper<AfterAbility> {
    List<AfterAbility> selectByUserId(Integer userId);
}
