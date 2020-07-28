package com.small.entity.person;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 电审记录表
 */
@Data
@Entity
@Table(name = "cs_person_phonecheck")
public class PersonPhonecheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**联系人信息表主键*/
    private Integer personrelationid;

    private Integer personid;
    /**
     * 电话类型
     * 1：关系人手机号码
     * 2：关系人家庭电话
     * 3：关系人单位电话
     */
    private Short phonetype;
    /**是否当地电话（1.是，0否）*/
    private Short islocal;
    /**拨打时间	*/
    private Date calldate;
    /**在审核新添加为：1, 在电催新添加为:2*/
    private Short newsaveflag;
    /**来源(电催添加)*/
    private String source;

    private Long projectid;
    /**联系人姓名*/
    private String relationname;
    /**关系Id*/
    private Integer relationship;

    private String phonenumberA;

    private String phonenumberM;
    /**电审记录*/
    @Lob
    private String phonetext;
}