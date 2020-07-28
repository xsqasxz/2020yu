package com.small.constant;

/**
 * @author xueshiqi
 * @since 2020/5/7
 * 贷后部分变量
 */
public class LoanState {
    /**逾期项目*/
    public final static short OVER_DUE = 11;
    /**总部贷后*/
    public final static String HEAD = "head";
    /**分行贷后*/
    public final static String BRANCH = "branch";
    /**回收贷后*/
    public final static String RECYCLE = "recycle";

    /**分行贷后查询8天权限*/
    public final static String BRANCH8 = "_branch_5";
    /**分行贷后查询9/40天权限*/
    public final static String BRANCH9 = "_branch6/35";
    /**贷后总权限*/
    public final static String ALL_SMALLLOAN = "_all_smallloan_role";
    /**分行_部门_跟进人*/
    public final static String ORGANIZATION_FOLLOWUP = "_organization_followUp_role";
    /**团队经理_跟进人*/
    public final static String TEAMMANAGER_FOLLOWUP = "_teamManager_followUp_role";
    /**维护人_跟进人*/
    public final static String MAINTAIN_FOLLOWUP = "_maintain_followUp_role";
}
