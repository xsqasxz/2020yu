package com.small.dto.after;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class AfterAbilityPowerDto {
    /**岗位id*/
    private Integer afterAbilityId;
    /**url地址id*/
    private List<Integer> afterPowerIds;
}
