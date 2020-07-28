package com.small.entity.project;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款计划表
 */
@Data
@Entity
@Table(name = "t_repayment_item")
public class RepaymentItem {

    @Id
    private Long id;
    /**利息*/
    private BigDecimal interest;
    /**期数*/
    private Integer issue;
    /**逾期天数*/
    private Integer overdueDay;
    /**本金*/
    private BigDecimal principal;
    /**本金余额*/
    private BigDecimal principalBalance;
    /**PREVIEW(预览):0;WAIT_REPAY(待还款):1;SQUARED(正常结清):2;OVERDUE(逾期):3;BAD_DEBT_SQUARED(坏账结清):4;OVERDUE_SQUARED(逾期结清):5;*/
    private Byte repaymentStatus;
    /**DEDUCT(p2p扣款):0;MANUAL(手动还款):1*/
    private Byte repaymentType;
    /**AUTOMATIC(自动扣划):1;MANUAL(手动扣划):2*/
    private Byte repaymentWay;
    /**计划还款日*/
    private Date planRepayDate;
    /**项目id*/
    private Long projectId;
    /**实际到账日*/
    private Date actualArrivalDate;
    /**已还金额*/
    private BigDecimal alreadyPayment;
    /**是否有代扣中条目*/
    private Boolean hasDeducting;
    /**未还金额*/
    private BigDecimal notPaidAmount;
    /**平台月供值(小贷计算出来的)*/
    private BigDecimal platformMonthlySupply;
    /**P2P本期未还金额*/
    private BigDecimal platformCurrentUnpaidAmount;
    /**P2P平台月供(平台返回的)*/
    private BigDecimal returnMonthlySupply;
    /**提前还款额*/
    private BigDecimal advanceRepayment;
    /**备注*/
    private String remarks;
}