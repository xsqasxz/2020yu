package com.small.mapper.project;

import com.small.entity.project.OverdueItem;
import tk.mybatis.mapper.common.Mapper;

public interface OverdueItemMapper extends Mapper<OverdueItem> {
    Double selectOverduePrincipalSum(Long projectId);
}