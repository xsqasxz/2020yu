package com.small.service.repayment.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.dto.repayment.RemindDto;
import com.small.entity.Dictionary;
import com.small.mapper.DictionaryMapper;
import com.small.mapper.project.RepaymentItemMapper;
import com.small.service.repayment.RemindService;
import com.small.utils.DateUtils;
import com.small.utils.OrgUtil;
import com.small.vo.repayment.RemindVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author xueshiqi
 * @since 2020/6/1
 */
@Service
public class RemindServiceImpl implements RemindService {

    @Resource
    private RepaymentItemMapper repaymentItemMapper;
    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public PageInfo<RemindVo> getRemind(RemindDto remindDto) {
        getRemindDto(remindDto);
        PageHelper.startPage(remindDto.getPageNum(), remindDto.getPageSize());
        return new PageInfo<>(repaymentItemMapper.getRemind(remindDto));
    }

    private void getRemindDto(RemindDto remindDto){
        Date startDate = DateUtils.parseDate(remindDto.getStartDate());
        if(startDate==null){
            startDate = new Date();
            remindDto.setStartDate(DateUtils.getDate());
        }
        remindDto.setStartDateContrast(DateUtils.getDaysBetween(new Date(),startDate));
        //查询数据字典得到
        Dictionary d = new Dictionary();
        d.setDickey("REMINDER_DATE_FOR_REPAYMENT");
        Dictionary dictionary = dictionaryMapper.selectOne(d);
        if (StringUtils.isNotBlank(dictionary.getItemvalue()) || StringUtils.isNumericSpace(dictionary.getItemvalue())) {
            remindDto.setRepaymentDate(Integer.valueOf(dictionary.getItemvalue()));
        }
        //提取出输入的逾期天数区间，如果只输入了一个数字，那就直接查询逾期这个数字的客户
        String overdueDay = remindDto.getOverdueDay();
        if (!StringUtils.isEmpty(overdueDay)) {
            if (overdueDay.trim().matches("^\\d+$")) {
                remindDto.setOverdueDayEnd(Integer.parseInt(overdueDay.trim()));
            } else if (overdueDay.trim().matches("^[0-9]+\\-[0-9]+$")) {
                remindDto.setOverdueDayBegin(Integer.parseInt(overdueDay.split("-")[0]));
                remindDto.setOverdueDayEnd(Integer.parseInt(overdueDay.split("-")[1]));
            }
        }else if (CollectionUtils.isEmpty(remindDto.getOrgIds()) && DateUtils.pastDays(startDate)==0){
            remindDto.setOverdueDay("0-30");
            //默认给0-30天
            remindDto.setOverdueDayBegin(0);
            remindDto.setOverdueDayEnd(30);
        }
//        remindDto.setOrgIds(OrgUtil.getOrgIds(remindDto.getOrgIds()));
    }
}
