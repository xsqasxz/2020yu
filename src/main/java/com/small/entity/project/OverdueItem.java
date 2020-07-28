package com.small.entity.project;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 逾期还款表
 */
@Data
@Entity
@Table(name = "t_overdue_item")
public class OverdueItem {
    @Id
    private Long id;

    /**逾期费用减免金额*/
    private Double remissionRate;

    /**实际到账日*/
    private Date actualArrivalDate;

    /**垫付服务费*/
    private BigDecimal advanceCharge;

    /**已还金额*/
    private BigDecimal alreadyPayment;

    /**是否有代扣中条目*/
    private Boolean hasDeducting;

    /**未还金额*/
    private BigDecimal notPaidAmount;

    /**逾期天数*/
    private Integer overdueDay;

    private Long projectId;

    private Long repaymentItemId;

    /**逾期天数减免*/
    private Integer overdueDayRebate;

    /**扣款手续费*/
    private BigDecimal deductionCharge;
}