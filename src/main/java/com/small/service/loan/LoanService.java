package com.small.service.loan;

import com.github.pagehelper.PageInfo;
import com.small.dto.loan.LoanCustomerDto;
import com.small.vo.loan.LoanCustomerDetailsVo;
import com.small.dto.loan.LoanDto;
import com.small.vo.loan.LoanVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author xueshiqi
 * @since 2020/5/8
 * 贷后管理
 */
@Validated
public interface LoanService {
    PageInfo<LoanVo> findByHeadLoan(LoanDto loanDto);

    PageInfo<LoanVo> findByBranchLoan(LoanDto loanDto);

    PageInfo<LoanVo> findByRecycleLoan(LoanDto loanDto);

    LoanCustomerDetailsVo findByLoanCustomerDetails(@NotNull(message = "项目id不可为空") Long projectId);

    void saveLoanCustomer(LoanCustomerDto loanCustomerDto);
}
