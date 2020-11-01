package com.small.dto.blog;

import com.small.dto.PageDto;
import lombok.Data;

@Data
public class BlogDto extends PageDto {
    private Integer frontHtmlId;
    /**模版类型*/
    private String htmlKeyword;
    /**是否更新*/
    private Boolean wantUpdate;
}
