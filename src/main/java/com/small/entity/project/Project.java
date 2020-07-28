package com.small.entity.project;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="sl_smallloan_project")
public class Project {
    @Id
    private Long projectid;

    /**
     * 业务品种
     */
    private String operationtype;

    /**
     * 流程类别
     */
    private String flowtype;

    /**
     * 我方主体类型
     */
    private String minetype;

    /**
     * 我方主体ID
     */
    private Long mineid;

    private String oppositetype;
    /**
     * 客户id
     */
    private Integer oppositeid;
    /**
     * 项目名称
     */
    private String projectname;
    /**
     * 项目编号
     */
    private String projectnumber;

    /**
     * 签约金额、放款金额
     */
    private BigDecimal projectmoney;

    private Short loantype;

    /**
     * 日期模型
     */
    private String datemode;

    /**
     * 签约日期
     */
    private Date startdate;

    /**
     * 原始的还款日期，如果展期到某天，就记录在展期表里
     */
    private Date intentdate;

    /**
     * 还款方式
     */
    private String accrualtype;

    /**
     * 贷款利率
     */
    private BigDecimal accrual;

    /**
     * 还款周期
     */
    private String payaccrualtype;
    /**
     * 逾期费率
     */
    private BigDecimal overduerate;

    /**
     * 是否允许提前还款
     */
    private Short isaheadpay;

    /**
     * 提前还款通知天数
     */
    private Short aheaddaynum;

    /**
     * 违约金比例
     */
    private BigDecimal breachrate;

    /**
     * 违约金额
     */
    private BigDecimal breachmoney;

    /**
     * 佣金方式
     */
    private Short commisiontype;

    /**
     * 项目上报时间
     */
    private Date commitdate;

    /**
     * 项目状态
     */
    /**
     * 项目状态(projectStatus)
     * 办理中贷款：0
     * 放款后贷款：1
     * 已完成贷款：2
     * 提前终止贷款：3
     * 展期申请中(未审批)：4
     * 通过展期申请(展期状态)：5
     * 未通过展期申请：6
     * 贷后监管中(未\):7
     * 完成贷后监管(已提交任务):9
     * 违约贷款：8
     * 已挂起项目：10(与多个不同的项目表以及和任务相关的表状态一致,都为10,避免不同的地方是不同的值,而本身所代表的意思一样。)
     * 逾期贷款: 11
     * 违约处理贷款: 12
     * 未知手动处理 13
     */
    private Short projectstatus;

    /**
     * 预计付息日
     */
    private Date payaccrualdate;

    /**
     * 利息总额
     */
    private BigDecimal accrualmoney;

    /**
     * 已还金额(本金)
     */
    private BigDecimal payprojectmoney;

    /**
     * 已还金额(利息)
     */
    private BigDecimal payaccrualmoney;

    /**
     * 平帐金额
     */
    private BigDecimal flatmoney;

    /**
     * 年净化利率
     */
    private BigDecimal annualnetprofit;

    private Long manageruser;

    /**
     * 小额贷,企业贷，业务品种
     */
    private String businesstype;

    /**
     * 日利华率
     */
    private BigDecimal dayofrate;

    /**
     * 日利化利息
     */
    private BigDecimal dayofaccrual;

    /**
     * 是否前置付息 0否 	1 是
     */
    private Integer ispreposepayaccrual;
    /**
     * 团队经理
     */
    private Long appuserid;

    /**
     * 管理咨询费率
     */
    private BigDecimal managementconsultingofrate;

    /**
     * 财务服务费率
     */
    private BigDecimal financeserviceofrate;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 币种
     */
    private Long currency;
    /**
     * 贷款用途
     */
    private Long purposetype;

    /**
     * 杂项费用收入
     */
    private BigDecimal incomechargemoney;

    /**
     * 咨询管理费用
     */
    private BigDecimal consultationmoney;

    /**
     * 杂项费用支出
     */
    private BigDecimal paychargemoney;

    /**
     * 财务服务费
     */
    private BigDecimal servicemoney;

    /**
     * 项目结束时间
     */
    private Date enddate;

    /**
     * 已还金额(杂项收入)
     */
    private BigDecimal payincomechargemoney;

    /**
     * 管理咨询费率 我方主体类型
     */
    private String managementconsultingminetype;

    /**
     * 管理咨询费率 我方主体Id
     */
    private Long managementconsultingmineid;

    /**
     * 财务服务费率 我方主体类型
     */
    private String financeserviceminetype;

    /**
     * 财务服务费率 我方主体ID
     */
    private Long financeservicemineid;

    /**
     * 杂项费用支出
     */
    private BigDecimal paypaychargemoney;

    /**
     * 平帐金额
     */
    private BigDecimal flatincomechargemoney;

    /**
     * 平帐金额
     */
    private BigDecimal flatpaychargemoney;

    /**
     * 项目经理名字
     */
    private String appusername;

    /**
     * 申请期限
     */
    private Integer payintentperiod;

    /**
     * 每期还款日
     */
    private Integer payintentperiodate;

    /**
     * 是否按还款日还款
     */
    private String isstartdatepay;

    /**
     * 逾期费率的计算方式,1按日2，按期
     */
    private String overdueratetype;

    /**
     * 用户登录记录
     */
    private Long companyid;

    /**
     * 推荐人
     */
    private String recommenduser;

    /**
     * 自定义天数
     */
    private Integer dayofeveryperiod;

    /**
     * 营销人员
     */
    private String recommenduserid;

    /**
     * 保存信息来源
     */
    private String infosourceid;

    /**
     * 财务对接时项目方款状态 ：0表示未点击放款按钮；1表示已经点击了放款按钮；2表示银行账户错误
     */
    private Integer states;

    /**
     * 贷款期限
     */
    private String loanlimit;

    /**
     * 主担保方式
     */
    private String assuretypeid;

    /**
     * 客户渠道
     */
    private String customerchannel;

    /**
     * 产品类别
     */
    private String producttypeid;

    /**
     * 审贷会全票通过后的项目金额
     */
    private BigDecimal projectmoneypass;

    /**
     * 逾期贷款利率(逾期贷款利率默认 贷款综合利率2倍，此利率为本金逾期时处罚利率)
     */
    private BigDecimal overduerateloan;

    /**
     * 投资人利率
     */
    private BigDecimal accrualnew;

    /**
     * 是否属于处于利率变更，展期，提前还款的款项计划重新生成：默认为0表示没有贷后流程，1表示处于展期流程中；2表示提前还款流程；3表示利率变更流程,7
     */
    private Integer isotherflow;

    private String accrualtypenew;

    private BigDecimal managementconsultingofratenew;

    /**
     * 咨询服务费率是否一次性前置付息
     */
    private Integer ispreposepayconsultingcheck;

    /**
     * 放款形式
     */
    private String loanformsid;

    /**
     * 业务分类：虚拟业务、正常业务、特殊业务。
     */
    private String businesscassify;

    /**
     * 是否一次性支付利息  0否 	1 是
     */
    private Integer isinterestbyonetime;

    /**
     * 年化利率
     */
    private BigDecimal yearaccrualrate;

    /**
     * 日化利率
     */
    private BigDecimal dayaccrualrate;

    /**
     * 合计利率
     */
    private BigDecimal sumaccrualrate;

    /**
     * 贷款利率(年)
     */
    private BigDecimal yearmanagementconsultingofrate;

    /**
     * 贷款利率(日)
     */
    private BigDecimal daymanagementconsultingofrate;

    /**
     * 贷款利率(合计)
     */
    private BigDecimal summanagementconsultingofrate;

    /**
     * 财务服务费率(年)
     */
    private BigDecimal yearfinanceserviceofrate;

    /**
     * 财务服务费率(日)
     */
    private BigDecimal dayfinanceserviceofrate;

    /**
     * 财务服务费率合计
     */
    private BigDecimal sumfinanceserviceofrate;

    /**
     * 项目属性    常规:common，虚拟:invented,特殊:special
     */
    private String projectproperties;

    private Long parentprojectid;

    private BigDecimal remainingmoney;

    private Short isdelivery;

    /**
     * 产品类型
     */
    private Long productid;

    /**
     * 投资人id
     */
    private String investorids;

    /**
     * 最后推介日期
     */
    private Date lastpresentdate;

    /**
     * 资金来源
     */
    private String fundresource;

    /**
     * 实际出资方式
     */
    private String investname;

    /**
     * 对接金额（自有资金）
     */
    private Long ownjointmoney;

    /**
     * 贷款利率（自有资金）
     */
    private Long ownaccrual;

    /**
     * 管理咨询费率（自有资金）
     */
    private Long ownmanagementconsultingofrate;

    /**
     * 财务服务费率（自有资金）
     */
    private Long ownfinanceserviceofrate;

    /**
     * 对接金额（平台资金）
     */
    private Long platformjointmoney;

    /**
     * 贷款利率（平台资金）
     */
    private Long platformaccrual;

    /**
     * 管理咨询费率（平台资金）
     */
    private Long platformmanagementconsultingofrate;

    /**
     * 财务服务费率（平台资金）
     */
    private Long platformfinanceserviceofrate;

    private Long flag;

    /**
     * 贷款用途
     */
    private String loanpurpose;

    private String intentrateend;

    /**
     * 期望利率
     */
    private String intentratestart;

    /**
     * 期望到位日期
     */
    private Date poupsedate;

    /**
     * 贷款期限
     */
    private Integer poupseperiod;

    private BigDecimal money;

    /**
     * 推介机构类型，目前是写死的
     */
    private String availabletype;

    /**
     * 推介机构ID
     */
    private Long availableid;

    /**
     * 申请时可用额度
     */
    private String credit;

    /**
     * 起息日
     */
    private Date startinterestdate;

    /**
     * 余额管理费率
     */
    private BigDecimal balancerate;

    /**
     * 风险保证金率
     */
    private BigDecimal riskrate;

    /**
     * 客户经理
     */
    private Long teammanagerid;

    /**
     * 客户经理姓名
     */
    private String teammanagername;

    /**
     * 所属分行id
     */
    private Long departid;

    /**
     * 分公司
     */
    private Long branchid;

    /**
     * 贷款用途  字符串，当需要手动录入的时候保存字符串
     */
    private String purposetypestr;

    /**
     * 关联EnterpriseBankId  一个客户有好几关联的银行账户，所以需要保存，同时，如果未指定则默认回填  主放款银行账户
     */
    private Long bankid;

    /**
     * 门店名称
     */
    private String departmentname;

    /**
     * B角成员Id
     */
    private String appuseridb;

    /**
     * B角成员名称   石药需要的
     */
    private String appusernameb;

    /**
     * 当然归属地
     */
    private String archivesbelonging;

    /**
     * 款项计算方案
     */
    private String fundscheme;

    /**
     * 推荐人
     */
    private String referee;

    /**
     * 项目终止原因
     */
    private Integer endreason;

    /**
     * 到账金额(审批额度)
     */
    private String actualmoney;

    /**
     * 拒单环节
     */
    private Integer singlelink;

    /**
     * 新旧系统识别标志(新系统录入的为 1)
     */
    private Integer infostateflag;

    /**
     * 申请金额
     */
    private Double applymonry;

    /**
     * 申请期限
     */
    private Integer applydate;

    /**
     * 决议服务费
     */
    private Double handlingcharge;

    /**
     * 决议放款条件
     */
    private String loancondition;

    /**
     * 线上项目编号
     */
    private String slprojectnumberol;

    /**
     * 服务费
     */
    private BigDecimal slprojecthandlingcharge;

    /**
     * 签约银行卡类型
     */
    private Long banktypeid;

    /**
     * 签约银行卡号
     */
    private String accountnumberA;

    private String accountnumberM;

    /**
     * 放款日期
     */
    private Date lenddate;

    /**
     * 维护人
     */
    private Long maintenanceid;

    /**
     * 是否允许续贷
     */
    private Boolean allowcontinueloan;

    /**
     * 贷款费率
     */
    private String lendrate;

    /**
     * 原团队经理
     */
    private Long maintainteamid;

    /**
     * 合同确认时间
     */
    private Date contractconfirmationtime;

    /**
     * 一次性手续费
     */
    private BigDecimal onetimefee;

    /**
     * 风险服务费收取方式(1.每期收费,2.期初收费)，默认为1
     */
    private Short riskscfeemodel;

    /**
     * 是否为固定风险服务费
     */
    private Short fixedriskscfee;

    /**
     * 居间服务费
     */
    private BigDecimal mediacyservicefee;

    /**
     * 担保服务费
     */
    private BigDecimal guaranteeservicefee;

    /**
     * 一次性手续费收取时间（1 放款时  2首期）
     */
    private Integer onetimefeecollecttype;

    /**
     * 月一次性手续费率
     */
    private BigDecimal mouthonetimerate;

    /**
     * 年化一次性手续费率
     */
    private BigDecimal yearonetimerate;

    /**
     * 保险费
     */
    private BigDecimal insuranceamout;

    /**
     * 月咨询服务费率
     */
    private BigDecimal mouthmediacyservicerate;

    /**
     * 年化咨询服务费率
     */
    private BigDecimal yearmediacyservicerate;

    /**
     * 月资产管理服务费率
     */
    private BigDecimal mouthguaranteeservicerate;

    /**
     * 年化资产管理服务费率
     */
    private BigDecimal yearguaranteeservicerate;

    /**
     * 是否保存过（0 未保存  1已保存）
     */
    private Integer issaved;

    /**
     * 放款渠道(1 达人贷   2 HDY  3 NWJR 4 LRKJ 5吉信)
     */
    private Integer loanchannel;

    /**
     * 合同签署类别
     */
    private Integer signaturecategory;

    /**
     * 进件渠道
     */
    private String incomingchannel;

    /**
     * 违约说明
     */
    private String breachcomment;

    /**
     * 资金款项修改情况备注
     */
    private String remarks;

    /**
     * 备注
     */
    private String loanremark;

    /**
     * 线上项目介绍
     */
    private String slprojectintruduceol;

    /**
     * 线上项目放款条件
     */
    private String slprojectloancondition;

    /**
     * 考察意见
     */
    private String investigateOpinions;

}