package com.small.service.loan.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.constant.LoanState;
import com.small.dto.loan.LoanCustomerDto;
import com.small.entity.project.OverdueProject;
import com.small.mapper.project.*;
import com.small.vo.loan.LoanCustomerDetailsVo;
import com.small.dto.loan.LoanDto;
import com.small.security.entity.SysUser;
import com.small.service.loan.LoanService;
import com.small.utils.OrgUtil;
import com.small.vo.loan.LoanVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xueshiqi
 * @since 2020/5/8
 * 贷后管理
 */
@Service
public class LoanServiceImpl implements LoanService {
    //客户项目信息
    @Resource
    private ProjectMapper projectMapper;
    //还款计划
    @Resource
    private RepaymentItemMapper repaymentItemMapper;
    //逾期明细
    @Resource
    private OverdueItemMapper overdueItemMapper;
    //展期明细
    @Resource
    private RepaymentExtensionMapper repaymentExtensionMapper;
    //逾期项目
    @Resource
    private OverdueProjectMapper overdueProjectMapper;


    @Override
    public PageInfo<LoanVo> findByHeadLoan(LoanDto loanDto) {
        loanDto.setProjectStatus(LoanState.OVER_DUE);
        loanDto.setProjectOverdueDayStart(40);
        loanDto.setLoanType(LoanState.HEAD);
        PageHelper.startPage(loanDto.getPageNum(), loanDto.getPageSize()).setOrderBy("op.project_overdue_day desc");
        getLoanDto(loanDto);
        return new PageInfo<>(projectMapper.selectByLoan(loanDto));
    }

    @Override
    public PageInfo<LoanVo> findByBranchLoan(LoanDto loanDto) {
        loanDto.setProjectStatus(LoanState.OVER_DUE);
        loanDto.setProjectOverdueDayStart(1);
        loanDto.setProjectOverdueDayEnd(40);
        loanDto.setLoanType(LoanState.BRANCH);
        PageHelper.startPage(loanDto.getPageNum(), loanDto.getPageSize()).setOrderBy("op.project_overdue_day desc");
        getLoanDto(loanDto);
        return new PageInfo<>(projectMapper.selectByLoan(loanDto));
    }

    @Override
    public PageInfo<LoanVo> findByRecycleLoan(LoanDto loanDto) {
        loanDto.setProjectStatus(LoanState.OVER_DUE);
        loanDto.setProjectOverdueDayStart(40);
        loanDto.setLoanType(LoanState.RECYCLE);
        PageHelper.startPage(loanDto.getPageNum(), loanDto.getPageSize()).setOrderBy("op.project_overdue_day desc");
        getLoanDto(loanDto);
        return new PageInfo<>(projectMapper.selectByLoan(loanDto));
    }

    @Override
    public LoanCustomerDetailsVo findByLoanCustomerDetails(Long projectId) {
        LoanCustomerDetailsVo loanCustomerDetailsVo = projectMapper.findByLoanCustomerDetails(projectId);
        LoanCustomerDetailsVo details = repaymentItemMapper.findByLoanCustomerDetails(projectId);
        /*逾期费用合计*/
        loanCustomerDetailsVo.setOverduePrincipalSum(overdueItemMapper.selectOverduePrincipalSum(projectId));
        /**展期未还金额合计*/
        loanCustomerDetailsVo.setDelayNotMoneySum(repaymentExtensionMapper.selectDelayNotMoneySumByProjectId(projectId));
        if(details!=null) {
            /*月还款额*/
            loanCustomerDetailsVo.setMonthlyPayment(details.getMonthlyPayment());
            /*本金余额*/
            loanCustomerDetailsVo.setBalancePrincipal(details.getBalancePrincipal());
            /*代扣日*/
            loanCustomerDetailsVo.setWithholdingDay(details.getWithholdingDay());
        }
        return loanCustomerDetailsVo;
    }

    @Override
    public void saveLoanCustomer(LoanCustomerDto loanCustomerDto) {
        OverdueProject overdueProject = new OverdueProject();
        overdueProject.setId(loanCustomerDto.getProjectId());
        overdueProject.setFalseInformation(loanCustomerDto.getFalseInformation()<0?null:loanCustomerDto.getFalseInformation());
        overdueProject.setFollowState(loanCustomerDto.getFollowState()<0?null:loanCustomerDto.getFollowState());
        overdueProject.setCaseState(loanCustomerDto.getCaseState()<0?null:loanCustomerDto.getCaseState());
        overdueProject.setCaseStateDetails(loanCustomerDto.getCaseStateDetails());
        overdueProjectMapper.updateById(overdueProject);
    }

    /**
     * 对权限部分进行封装
     */
    public void getLoanDto(LoanDto loanDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = (SysUser)authentication.getPrincipal();
//        List<Long> accountIds =  user.getNewPositionList().stream().map(NewPosition :: getAccountId).collect(Collectors.toList());
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if(LoanState.BRANCH.equals(loanDto.getLoanType())) {
            //_branch_5  分行贷后查询8天权限
            if (authorities.contains(new SimpleGrantedAuthority(LoanState.BRANCH8))) {
                loanDto.setProjectOverdueDayEnd(8);
            }
            //_branch6/35  分行贷后查询9/40天权限
            if (authorities.contains(new SimpleGrantedAuthority(LoanState.BRANCH9))) {
                loanDto.setProjectOverdueDayStart(9);
            }
        }
        //只要不是贷后总权限，全部都要有
        if(!authorities.contains(new SimpleGrantedAuthority(LoanState.ALL_SMALLLOAN))){
//            loanDto.setFollowUpUserId(accountIds);
            //默认给上跟进人权限 ，其他权限都是与跟进人为或者状态
            //分行 部门 跟进人疑似无效，因前面默认加上了权限内的分行查询
            if(authorities.contains(new SimpleGrantedAuthority(LoanState.ORGANIZATION_FOLLOWUP))){
//                loanDto.setOrganizationId(OrgUtil.getOrgIds(null));
            }
            //团队经理权限
            if(authorities.contains(new SimpleGrantedAuthority(LoanState.TEAMMANAGER_FOLLOWUP))){
//                loanDto.setAppUserManagerId(accountIds);
            }
            //维护人权限
            if(authorities.contains(new SimpleGrantedAuthority(LoanState.MAINTAIN_FOLLOWUP))){
//                loanDto.setTeamManagerId(accountIds);
            }
        }
    }


}
