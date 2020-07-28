package com.small.security.dao.impl;

import com.small.entity.after.AfterAbility;
import com.small.entity.after.AfterPower;
import com.small.mapper.after.AfterAbilityMapper;
import com.small.mapper.after.AfterPowerMapper;
import com.small.security.dao.SysRoleDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/4/24
 * 用户权限部分
 */
@Service
public class SysRoleDaoImpl implements SysRoleDao {

    @Resource
    private AfterPowerMapper afterPowerMapper;
    @Resource
    private AfterAbilityMapper afterAbilityMapper;

    @Override
    public List<AfterPower> findById(Integer abilityId) {
        return afterPowerMapper.selectAfterPowerByAbilityId(abilityId);
    }

    @Override
    public List<AfterAbility> findAfterAbilityByUserId(Integer userId) {
        return afterAbilityMapper.selectByUserId(userId);
    }

    @Override
    public String getAbilityNameById(Integer abilityId) {
        AfterAbility afterAbility = afterAbilityMapper.selectByPrimaryKey(abilityId);
        if(afterAbility!=null) {
            return afterAbility.getAbilityName();
        }else{
            return null;
        }
    }

//    @Override
//    public List<Organizing> findOrganizingByUserId(Long userId) {
//        return organizingMapper.selectByUserId(userId);
//    }
}
