package com.small.vo.after;

import com.small.entity.after.AfterAbility;
import lombok.Data;

/**
 * @author xueshiqi
 * @since 2020/9/11
 */
@Data
public class AfterAbilityVo {
    private Integer id;
    /**岗位名称*/
    private String abilityName;
    /**是否有效*/
    private Boolean abilityEffective;
    /**是否选中的*/
    private Boolean abilityChecked;

    public AfterAbilityVo() {
    }

    public AfterAbilityVo(AfterAbility afterAbility) {
        this.id = afterAbility.getId();
        this.abilityName = afterAbility.getAbilityName();
        this.abilityEffective = afterAbility.getAbilityEffective();
    }
}
