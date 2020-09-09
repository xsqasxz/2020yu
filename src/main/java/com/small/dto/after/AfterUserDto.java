package com.small.dto.after;

import com.small.dto.PageDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 用户帐号管理
 */
@Data
public class AfterUserDto extends PageDto {
    /**是否有效*/
    private Boolean userEffective;
    /**用户名*/
    @Size(max = 5,message = "用户名长度不可以超过5")
    private String userName;
    /**密码*/
    @Size(min = 5,max = 20,message = "用户密码在5-20位之间")
    private String resetPassword;
    /**用户id*/
    private Integer afterUserId;

    public static void getAfterUserDto(AfterUserDto afterAbilityDto){
        if(StringUtils.isBlank(afterAbilityDto.getUserName())){
            afterAbilityDto.setUserName(null);
        }
    }
}
