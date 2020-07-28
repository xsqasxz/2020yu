package com.small.dto.loan;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author xueshiqi
 * @since 2020/5/14
 * 贷后客户dto
 */
@Data
public class LoanCustomerDto {
    @NotNull(message = "项目编号不可为空")
    private Long projectId;
    /**虚假信息*/
    @Min(value = -1,message = "请选择正确的虚假信息")
    @Max(value = 9,message = "请选择正确的虚假信息")
    private Byte falseInformation;
    /**跟进状态*/
    @Min(value = -1,message = "请选择正确的跟进状态")
    @Max(value = 13,message = "请选择正确的跟进状态")
    private Byte followState;
    /**逾期原因*/
    @Min(value = -1,message = "请选择正确的逾期原因")
    @Max(value = 14,message = "请选择正确的逾期原因")
    private Byte caseState;
    /**逾期详情*/
    @Length(max =250,message = "逾期原因最多可以写250个字")
    private String caseStateDetails;
}
