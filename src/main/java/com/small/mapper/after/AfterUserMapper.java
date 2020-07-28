package com.small.mapper.after;

import com.small.entity.after.AfterUser;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author xueshiqi
 * @since 2020/7/27
 */
public interface AfterUserMapper extends Mapper<AfterUser> {
    String getUserName(Long userId);
}
