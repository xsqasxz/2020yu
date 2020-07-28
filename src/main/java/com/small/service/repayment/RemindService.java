package com.small.service.repayment;

import com.github.pagehelper.PageInfo;
import com.small.dto.repayment.RemindDto;
import com.small.vo.repayment.RemindVo;

/**
 * @author xueshiqi
 * @since 2020/6/1
 */
public interface RemindService {
    PageInfo<RemindVo> getRemind(RemindDto remindDto);
}
