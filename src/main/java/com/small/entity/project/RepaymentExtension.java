package com.small.entity.project;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
* 还款展期表
* @author xueshiqi
*/
@Data
@Entity
@Table(name="t_repayment_extension")
public class RepaymentExtension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private Long repaymentItemId;
    /**展期金额*/
    private Double delayMoney;
    /**展期未还金额*/
    private Double delayNotMoney;
    /**创建人*/
    private Long createUserId;
    /**创建时间*/
    private Date createDate;
}