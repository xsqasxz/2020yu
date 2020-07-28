package com.small.security.entity;

import com.small.entity.after.AfterAbility;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 登录帐号
 * @author xueshiqi
 * @since 2020/5/7
 */

public class SysUser extends User implements Serializable {
    /**
     * 当前登录id
     */
    private Integer userId;
    /**
     * 当前权限名称
     */
    private String abilityName;

    private List<AfterAbility> afterAbilitieList;

    public SysUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer userId ,String abilityName,List<AfterAbility> afterAbilitieList) {
        super(username, password, true, true, true, true, authorities);
        this.userId = userId;
        this.abilityName = abilityName;
        this.afterAbilitieList = afterAbilitieList;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<AfterAbility> getAfterAbilitieList() {
        return afterAbilitieList;
    }

    public void setAfterAbilitieList(List<AfterAbility> afterAbilitieList) {
        this.afterAbilitieList = afterAbilitieList;
    }
}
