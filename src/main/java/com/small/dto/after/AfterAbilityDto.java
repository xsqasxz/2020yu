package com.small.dto.after;

import com.small.dto.PageDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Size;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 岗位管理
 */
@Data
public class AfterAbilityDto extends PageDto {
    /**是否有效*/
    private Boolean abilityEffective;
    /**岗位名称*/
    @Size(max = 5,message = "岗位名称长度不可以超过5")
    private String abilityName;

    public static void getAfterAbilityDto(AfterAbilityDto afterAbilityDto){
        if(StringUtils.isBlank(afterAbilityDto.getAbilityName())){
            afterAbilityDto.setAbilityName(null);
        }
    }
}
