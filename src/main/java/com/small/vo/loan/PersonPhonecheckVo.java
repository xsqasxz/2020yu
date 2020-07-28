package com.small.vo.loan;

import com.small.entity.person.PhoneUrgeRecord;
import lombok.Data;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/5/25
 * 查询电催全部记录
 */
@Data
public class PersonPhonecheckVo {

    /**关系*/
    public Integer relationship;
    /**姓名*/
    public String relationname;
    /**
     * 电话类型
     * 1：关系人手机号码
     * 2：关系人家庭电话
     * 3：关系人单位电话
     */
    private Short phonetype;
    /**加密后的电话号码*/
    private String phonenumberA;
    /**评论明细*/
    private List<PhoneUrgeRecord> list;
}
