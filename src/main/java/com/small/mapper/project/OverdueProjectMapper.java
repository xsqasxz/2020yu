package com.small.mapper.project;

import com.small.entity.project.OverdueProject;
import tk.mybatis.mapper.common.Mapper;

public interface OverdueProjectMapper extends Mapper<OverdueProject> {
    int updateById(OverdueProject overdueProject);
}