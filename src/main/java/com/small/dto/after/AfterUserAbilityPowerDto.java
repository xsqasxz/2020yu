package com.small.dto.after;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AfterUserAbilityPowerDto {
    /**用户id*/
    @NotNull(message = "用户id不可为空")
    private Integer afterUserId;
    /**岗位集合*/
    @NotNull(message = "岗位集合不可为空")
    private List<Integer> afterAbilityIds;
}
