package com.small.dto.after;

import com.small.dto.PageDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 功能管理
 */
@Data
public class AfterPowerDto extends PageDto {
    /**URL地址*/
    @Size(max = 30,message = "URL地址长度不可以超过30位")
    private String powerUrl;
    /**别名*/
    @Size(max = 5,message = "别名长度不可以超过5")
    private String authority;

    public static void getAfterPowerDto(AfterPowerDto afterAbilityDto){
        if(StringUtils.isBlank(afterAbilityDto.getAuthority())){
            afterAbilityDto.setAuthority(null);
        }
        if(StringUtils.isBlank(afterAbilityDto.getPowerUrl())){
            afterAbilityDto.setPowerUrl(null);
        }
    }
}
