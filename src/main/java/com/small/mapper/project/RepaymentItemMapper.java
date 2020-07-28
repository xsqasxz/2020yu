package com.small.mapper.project;

import com.small.dto.repayment.RemindDto;
import com.small.entity.project.RepaymentItem;
import com.small.vo.loan.LoanCustomerDetailsVo;
import com.small.vo.repayment.RemindVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RepaymentItemMapper extends Mapper<RepaymentItem> {
    LoanCustomerDetailsVo findByLoanCustomerDetails(Long projectId);

    List<RemindVo> getRemind(RemindDto remindDto);
}