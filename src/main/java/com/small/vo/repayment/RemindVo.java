package com.small.vo.repayment;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xueshiqi
 * @since 2020/6/1
 * 还款提醒vo
 */
@Data
public class RemindVo {

    private Long projectId;

    /**
     * 计划还款日
     */
    private Date planRepayDate;
    /**
     * 还款期数
     */
    private int issue;
    /**
     * 所属分行
     */
    private String departmentName;
    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 本月还款额
     */
    private BigDecimal monthlyRepayment;
    /**
     * 客户手机号 加密
     */
    private String customerPhone;

    /**
     * 展期金额
     */
    private Double repaymentAmount;

    /**客户id*/
    private Long personId;
    /**
     * 维护人id
     */
    private Long maintenanceId;

    /*逾期天数*/
    private Integer overdueDay;

    /*性别*/
    private String sex;
}
