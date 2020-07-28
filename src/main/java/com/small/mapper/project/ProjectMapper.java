package com.small.mapper.project;

import com.small.vo.loan.LoanCustomerDetailsVo;
import com.small.dto.loan.LoanDto;
import com.small.entity.project.Project;
import com.small.vo.loan.LoanVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProjectMapper extends Mapper<Project> {
    List<LoanVo> selectByLoan(LoanDto headLoanDto);

    LoanCustomerDetailsVo findByLoanCustomerDetails(Long projectId);
}