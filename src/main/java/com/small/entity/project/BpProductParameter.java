package com.small.entity.project;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品类型
 */
@Data
@Entity
public class BpProductParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**产品名称*/
    private String productname;

    /**借款人类型*/
    private String borrowertype;

    /**还款方式*/
    private String accrualtype;

    /**还款周期*/
    private String payaccrualtype;

    /**贷款期数*/
    private Integer payintentperiod;

    /**前置付息*/
    private Integer ispreposepayaccrual;

    /**一次性支付全部利息*/
    private Integer isinterestbyonetime;

    /**是否按还款日还款*/
    private String isstartdatepay;

    /**每期还款日*/
    private Integer payintentperiodate;

    /**年化利率*/
    private BigDecimal yearaccrualrate;

    /**月化利率*/
    private BigDecimal accrual;

    /**日化利率*/
    private BigDecimal dayaccrualrate;

    /**合计利率*/
    private BigDecimal sumaccrualrate;

    /**产品描述*/
    private String productdescribe;

    /**创建时间*/
    private Date createtime;

    /**产品状态（0：删除， 1：使用中）*/
    private Short productstatus;

    private Long useareaid;

    private String useareaname;

    private Long incomspeedid;

    private String incomspeedname;

    private Long loancostid;

    private String loancostname;

    /**主体类别*/
    private String minetype;

    /**主体Id*/
    private Long mineid;

    /**管理咨询费率(年)*/
    private BigDecimal yearmanagementconsultingofrate;

    /**管理咨询费率(月)*/
    private BigDecimal managementconsultingofrate;

    /**管理咨询费率(日)*/
    private BigDecimal daymanagementconsultingofrate;

    /**财务服务费率(年)*/
    private BigDecimal yearfinanceserviceofrate;

    /**财务服务费率(月)*/
    private BigDecimal financeserviceofrate;

    /**财务服务费率(日)*/
    private BigDecimal dayfinanceserviceofrate;

    /**自定义每一期的天数*/
    private Byte dayofeveryperiod;

    private String businesstype;

    private String operationtype;

    private String fundscheme;

    private String datemode;

    /**违约金比例*/
    private BigDecimal breachrate;

    /**逾期贷款利率(逾期贷款利率默认 贷款综合利率2倍，此利率为本金逾期时处罚利率)*/
    private BigDecimal overduerateloan;

    /**逾期费率*/
    private BigDecimal overduerate;

    /**产品大类*/
    private String producttype;

}