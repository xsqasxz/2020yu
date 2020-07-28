package com.small.entity.person;

import com.small.utils.VestaGenId;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

/**
 * 关系人联系记录表
 */
@Data
@Entity
@Table(name = "t_phone_urge_record")
public class PhoneUrgeRecord {
    @Id
    @KeySql(genId = VestaGenId.class)
    private Integer id;

    /**联系时间*/
    private Date contactDate;
    /**联系人*/
    private Long contacterId;
    /**逾期项目实体*/
    private Long overdueProjectId;
    /**电话核查*/
    private Long phoneCheckId;
    /**联系内容*/
    @Lob
    private String contactContent;
}