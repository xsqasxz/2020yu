package com.small.security.dao.impl;

import com.small.entity.after.AfterUser;
import com.small.mapper.after.AfterUserMapper;
import com.small.security.dao.SysUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xueshiqi
 * @since 2020/4/24
 * 用户登录
 */
@Service
public class SysUserDaoImpl implements SysUserDao {
    @Resource
    private AfterUserMapper afterUserMapper;

    @Override
    public AfterUser findByName(String userName) {
        AfterUser afterUser = new AfterUser();
        afterUser.setUserName(userName);
        //帐号是否有效
        afterUser.setUserEffective(false);
        return afterUserMapper.selectOne(afterUser);
    }
}
