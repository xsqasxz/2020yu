package com.small.dto.loan;

import com.small.dto.PageDto;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author xueshiqi
 * @since 2020/5/21
 */
@Data
public class PersonPhonecheckDto extends PageDto {
    /**电催id*/
    private Long personPhonecheckId;
    /**关系id*/
    @NotNull(message = "与客户关系不可以为空")
    private Integer relationship;
    /**联系人姓名*/
    @NotNull(message = "联系人姓名不可为空")
    @Size(min = 2,max = 5,message = "联系人姓名必须在2-5位")
    private String relationname;
    /**
     * 电话类型
     * 1：关系人手机号码
     * 2：关系人家庭电话
     * 3：关系人单位电话
     */
    @NotNull(message = "电话类型不可为空")
    @Min(value = 1,message = "请选择正确的电话类型")
    @Max(value = 3,message = "请选择正确的电话类型")
    private Short phonetype;

    /**电话号码*/
    @NotNull(message = "电话号码不可为空")
    @Pattern(regexp = "(^[0-9]{3,4}\\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\\([0-9]{3,4}\\)[0-9]{3,8}$)|(^[1][0-9]{10}$)",message = "请填写正确的电话号码")
    private String phonenumber;
    /**来源*/
    @Size(max = 50,message = "来源不可超过50个字")
    private String source;
    /**关系人id*/
    private Integer personrelationid;
    /**客户id*/
    @NotNull(message = "客户id不可为空")
    private Integer personid;
    /**项目id*/
    @NotNull(message = "项目id不可为空")
    private Long projectid;
}
