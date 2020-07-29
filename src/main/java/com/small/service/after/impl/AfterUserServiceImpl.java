package com.small.service.after.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterUserDto;
import com.small.entity.after.AfterUser;
import com.small.mapper.after.AfterUserMapper;
import com.small.service.after.AfterUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xueshiqi
 * @since 2020/7/29
 * 用户帐号管理
 */
@Service
public class AfterUserServiceImpl implements AfterUserService {
    @Resource
    private AfterUserMapper afterUserMapper;
    @Override
    public PageInfo<AfterUser> getAfterUser(AfterUserDto afterUserDto) {
        AfterUserDto.getAfterUserDto(afterUserDto);
        PageHelper.startPage(afterUserDto.getPageNum(), afterUserDto.getPageSize());
        return new PageInfo<>(afterUserMapper.getAfterUser(afterUserDto));
    }
}
