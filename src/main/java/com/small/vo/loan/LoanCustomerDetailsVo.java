package com.small.vo.loan;

import lombok.Data;

import java.util.Date;

/**
 * @author xueshiqi
 * @since 2020/5/13
 * 客户详情dto
 */
@Data
public class LoanCustomerDetailsVo {
    /**客户id*/
    private Long personId;

    /**客户姓名*/
    private String customerName;

    /**客户身份证号码 需要解密*/
    private String cardnumber;

    /**分行*/
    private String departMentName;

    /**借款金额*/
    private Double projectMoney;

    /**到账金额*/
    private Double actualMoney;

    /**产品类型*/
    private Long productId;
    /**产品类型名称 需单独查询*/
    private String productName;

    /**签约日期*/
    private Date startDate;

    /**借款期限*/
    private Integer payIntentPeriod;

    /**已还期数*/
    private  Integer payOffCount;

    /**逾期日期*/
    private Date earliestOverdueDate;

    /**未还金额*/
    private Double sumNotPaidAmount;

    /**跟进状态*/
    private Short followState;

    /**逾期原因*/
    private Short caseState;

    /**虚假信息*/
    private Short falseInformation;

    /**逾期详情*/
    private String caseStateDetails;

    /*月还款额*/
    private Double monthlyPayment;
    /*本金余额*/
    private Double balancePrincipal;
    /*代扣日*/
    private Integer withholdingDay;
    /*逾期费用合计*/
    private Double overduePrincipalSum;
    /*展期未还*/
    private Double delayNotMoneySum;
}
