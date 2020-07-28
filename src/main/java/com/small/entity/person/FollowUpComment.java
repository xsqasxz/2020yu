package com.small.entity.person;

import com.small.utils.VestaGenId;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

/**
 * 外访评论
 */
@Data
@Entity
@Table(name = "t_follow_up_comment")
public class FollowUpComment {
    @Id
    @KeySql(genId = VestaGenId.class)
    private Integer id;
    /**评论时间*/
    private Date commentDate;
    /**外访人*/
    private Long commentManId;
    /**逾期跟进记录*/
    private Long overdueProjectId;
    /**评论内容*/
    @Lob
    private String commentContent;
}