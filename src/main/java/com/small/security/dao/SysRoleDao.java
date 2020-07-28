package com.small.security.dao;

import com.small.entity.after.AfterAbility;
import com.small.entity.after.AfterPower;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/4/24
 * 用户权限部分
 */
public interface SysRoleDao {
    /**
     * 得到对应岗位id的权限
     * @param userId 岗位id
     * @return 返回岗位权限
     */
    List<AfterPower> findById(Integer userId);

    /**
     * 跟进登录帐号查询该用户全部的帐号用于切换帐号使用
     * @param userId 用户id
     * @return 返回全部帐号
     */
    List<AfterAbility> findAfterAbilityByUserId(Integer userId);

    String getAbilityNameById(Integer abilityId);

    /**
     * 根据登录id查询该帐号下面全部的
     * @param userId
     * @return
     */
//    List<Organizing> findOrganizingByUserId(Long userId);
}
