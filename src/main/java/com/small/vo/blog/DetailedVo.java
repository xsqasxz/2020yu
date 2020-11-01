package com.small.vo.blog;

import lombok.Data;

import java.util.Date;

/**
 * 茶座部分前端展示
 */
@Data
public class DetailedVo {

    private Integer frontHtmlId;

    /**标题（模版名称）*/
    private String htmlName;

    private String htmlKeyword;

    /**模版修改时间 如果为空就使用创建时间*/
    private Date detailedDate;
}
