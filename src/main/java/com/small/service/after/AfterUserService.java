package com.small.service.after;

import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterUserDto;
import com.small.entity.after.AfterUser;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 用户帐号管理
 */
public interface AfterUserService {
    PageInfo<AfterUser> getAfterUser(AfterUserDto afterUserDto);
}
