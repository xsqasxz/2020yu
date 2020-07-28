package com.small.dto.repayment;

import com.small.dto.PageDto;
import lombok.Data;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/6/1
 * 还款提醒dto
 */
@Data
public class RemindDto extends PageDto {

    /**起止时间*/
    //你输入的逾期天数查询格式有误,正确格式为: n-n 或 n
    private String startDate;

    private Long startDateContrast;

    /**所属分行id集合*/
    private List<Long> orgIds;

    /**逾期天数(范围)*/
    private String overdueDay;


    private Integer overdueDayBegin;
    private Integer overdueDayEnd;
    /**天数权限，默认给7天*/
    private Integer repaymentDate=7;
    /**客户姓名*/
    private String personName;
    /**提醒次数*/
    private Integer remindCount;
}
