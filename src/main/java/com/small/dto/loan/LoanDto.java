package com.small.dto.loan;

import com.small.dto.PageDto;
import lombok.Data;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/5/8
 * 贷后管理部分
 */
@Data
public class LoanDto extends PageDto {
    /**
     * 客户名称
     */
    @Size(max = 5 ,message = "客户名称不可以超过5个字")
    private String customerName;

    /**
     * 所属分行id集合
     */
    private List<Long> orgIds;

    /**
     * 项目状态
     */
    private Short projectStatus;

    /**
     * 逾期开始时间
     */
    private Integer projectOverdueDayStart;

    /**
     * 逾期结束时间
     */
    private Integer projectOverdueDayEnd;

    /**
     * 类型 head：总部贷后 branch：分行贷后 recycle：回收贷后
     */
    private String loanType;

    /**跟进人集合*/
    private List<Long> followUpUserId;
    /**团队经理集合*/
    private List<Long> appUserManagerId;
    /**权限下分行*/
    private List<Long> organizationId;
    /**维护人集合*/
    private List<Long> teamManagerId;
}
