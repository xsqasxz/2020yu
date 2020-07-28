package com.small.entity.person;

import lombok.Data;

import javax.persistence.*;

/**
 * 客户关系人表
 */
@Data
@Entity
@Table(name = "cs_person_relation")
public class PersonRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String relationname;

    private Integer relationship;
    /**关系人家庭电话 加密*/
    private String relationphoneA;
    /**关系人家庭电话 签名*/
    private String relationphoneM;
    /**关系人手机号码 加密*/
    private String relationcellphoneA;
    /**关系人手机号码 签名*/
    private String relationcellphoneM;

    private String relationshipvalue;

    private Integer personid;
    /**职业*/
    private String relationprofession;
    /**住址*/
    private String relationaddress;
    /**单位电话 加密*/
    private String relationcompanyphoneA;
    /**单位电话 签名*/
    private String relationcompanyphoneM;
    /**工作单位*/
    private String relationjobcompany;
    /**单位地址*/
    private String relationjobaddress;
    /**0 家庭联系人  1工作证明人 2紧急联系人*/
    private String flag;
    /**是否知晓其借款*/
    private String isknowloan;
    /**身份证号码 加密*/
    private String relationcardnumberA;
    /**身份证号码 签名*/
    private String relationcardnumberM;
    /**月收入*/
    private Double jobincome;
    /**住址 加密*/
    private String relationaddressA;
    /**住址 签名*/
    private String relationaddressM;
}