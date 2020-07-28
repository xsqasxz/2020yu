package com.small.vo.loan;

import lombok.Data;

/**
 * @author xueshiqi
 * @since 2020/5/8
 * 总部贷后管理
 */
@Data
public class LoanVo {
    private Long projectId;

    /**
     * 项目编号
     */
    private String  projectNumber;
    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 分行名称
     */
    private String departMentName;

    /**
     * 团队经理名称
     */
    private String appUserName;

    /**
     * 客户经理
     */
    private String teamManagerName;

    /**
     * 维护人id
     */
    private Long maintenanceId;

    /**
     * 维护人姓名
     */
    private String maintenanceName;

    /**
     * 逾期天数
     */
    private Integer projectOverdueDay;

    /**
     * 未还款金额
     */
    private Double sumNotPaidAmount;

    /**
     * 跟进人id
     */
    private Long lastFollowUpUserId;

    /**
     * 跟进人姓名
     */
    private String lastFollowUpUserName;
}
