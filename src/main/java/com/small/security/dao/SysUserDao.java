package com.small.security.dao;

import com.small.entity.after.AfterUser;

/**
 * @author xueshiqi
 * @since 2020/4/24
 * 用户登录
 */
public interface SysUserDao {
    /**
     * 得到登录帐号
     * @param userName 传入帐号名称
     * @return 返回有效的帐号，如果同帐号多个有效，就报异常
     */
    AfterUser findByName(String userName);
}
