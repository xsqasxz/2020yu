package com.small.dto.front;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author xueshiqi
 * @since 2020/10/14
 * 文章发布
 */
@Data
public class FrontReleaseDto {

    private Integer frontReleaseId;
    /**模版名称*/
    @Size(max = 300,message = "名称不可超过300")
    private String htmlName;
    /**0：模版 1：首页模版 2：详情模版*/
    @Min(value = 0,message = "模版类型必须在0-2之间")
    @Max(value = 2,message = "模版类型必须在0-2之间")
    private Integer htmlType;
    /**模版关键字，用于内联项目*/
    @Size(max = 50,message = "模版过长50以内")
    private String htmlKeyword;
    /**HTML的内容*/
    @Size(max = 65535,message = "内容太长了")
    private String htmlText;
    /**是否生效*/
    private Boolean takeEffect;
    /**模版生成的url地址 类型为详情模版时专用*/
    @Size(max = 300,message = "url地址不可超过300")
    private String htmlUrl;

}
