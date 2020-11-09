package com.small.dto.front;

import com.small.dto.PageDto;
import lombok.Data;

/**
 * @author xueshiqi
 * @since 2020/10/16
 * 静态页面管理
 */
@Data
public class FrontPageDto extends PageDto {
    private String htmlName;
    private Integer htmlType;
    private String htmlKeyword;
    private Boolean takeEffect;
    private Integer frontHtmlId;
}
