package com.small.vo.after;

import com.small.entity.after.AfterPower;
import lombok.Data;

/**
 * @author xueshiqi
 * @since 2020/9/11
 */
@Data
public class AfterPowerVo {

    private Integer id;
    /**权限名称*/
    private String authority;
    /**别名*/
    private String powerAlias;
    /**级别*/
    private Integer powerLevel;
    /**对应的url地址*/
    private String powerUrl;
    /**是否选中的*/
    private Boolean abilityChecked;

    public AfterPowerVo(AfterPower afterPower) {
        this.id = afterPower.getId();
        this.authority = afterPower.getAuthority();
        this.powerAlias =afterPower.getPowerAlias();
        this.powerLevel = afterPower.getPowerLevel();
        this.powerUrl = afterPower.getPowerUrl();
    }

    public AfterPowerVo() {

    }
}
