package com.small.dto.after;

import com.small.dto.PageDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Size;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 功能管理
 */
@Data
public class AfterPowerDto extends PageDto {
    /**URL地址*/
    @Size(max = 100,message = "URL地址长度不可以超过100位")
    private String powerUrl;
    /**名称*/
    @Size(max = 15,message = "名称长度不可以超过15")
    private String authority;
    /**别名*/
    @Size(max = 15,message = "别名长度不可以超过15")
    private String powerAlias;

    private Integer afterPowerId;

    public static void getAfterPowerDto(AfterPowerDto afterAbilityDto){
        if(StringUtils.isBlank(afterAbilityDto.getAuthority())){
            afterAbilityDto.setAuthority(null);
        }
        if(StringUtils.isBlank(afterAbilityDto.getPowerUrl())){
            afterAbilityDto.setPowerUrl(null);
        }
    }
}
