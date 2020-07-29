package com.small.mapper.after;

import com.small.dto.after.AfterUserDto;
import com.small.entity.after.AfterUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/7/27
 * 用户帐号管理
 */
public interface AfterUserMapper extends Mapper<AfterUser> {
    String getUserName(Long userId);

    List<AfterUser> getAfterUser(AfterUserDto afterUserDto);
}
