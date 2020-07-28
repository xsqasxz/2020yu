package com.small.mapper.project;

import com.small.entity.project.RepaymentExtension;
import tk.mybatis.mapper.common.Mapper;

public interface RepaymentExtensionMapper extends Mapper<RepaymentExtension> {
    Double selectDelayNotMoneySumByProjectId(Long projectId);
}