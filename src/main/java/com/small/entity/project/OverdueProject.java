package com.small.entity.project;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "t_overdue_project")
public class OverdueProject {
    @Id
    private Long id;

    /**
     * 最早一期逾期的还款日
     */
    private Date earliestOverdueDate;

    /**
     * 跟进状态
     * 0失联 1找到本人 2找到加入 3承诺还款 4违背承诺 5寻找中 6结清
     * 7投诉 8其他 9谈判中 10建议外访 11新开案件 12建议委外 13Pend
     */
    private Byte followState;

    /**
     * 跟进人id
     */
    private Long followUpUserId;

    /**
     * 项目逾期发生期数
     */
    private Integer happenedOverdue;

    /**
     * 项目逾期天数
     */
    private Integer projectOverdueDay;

    /**
     * 项目逾期状态（业务逻辑定义）
     */
    private String projectOverdueStatust;

    /**
     * 项目未还款金额总计(所有逾期未还款金额之和)
     */
    private BigDecimal sumNotPaidAmount;

    /**
     * 逾期次数
     */
    private Integer overdueCount;

    /**
     * 逾期已还款金额总计
     */
    private BigDecimal sumAlreadyPaidAmount;

    /**
     * 是否是跟进人
     */
    private Boolean isFollowUpUserChange;

    /**
     * 回收状态 SELF 只有, ENTRUST 委外,RECYCLE 回收
     */
    private String followUpType;

    /**
     * 委外id
     */
    private Long entrustId;

    /**
     * 逾期原因
     * 0失联 1可联 2离职搬家 3跑路倒闭 4虚假资料
     */
    private Byte caseState;

    /**
     * 虚假信息
     * 0无虚假信息 1包装客户 2经营不善 3对外投资失败 4生意被政府取缔 5主要下游企业倒闭
     * 6违法被刑拘 7过度消费 8不良嗜好 9意外事故 10重大疾病 11其他
     */
    private Byte falseInformation;

    /**
     * 最后跟进时间(电催最新时间)
     */
    private Date lastFollowDate;

    /**
     * 最后跟进时间(外访最新时间)
     */
    private Date abroadLastFollowDate;

    /**
     * 最后跟进人id
     */
    private Long lastFollowUpUserId;

    /**
     * 逾期原因详情
     */
    private String caseStateDetails;

    /**
     * 微信添加情况
     */
    private String wechatSaveMobile;
}