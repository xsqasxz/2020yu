package com.small.service.after.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.dto.after.AfterUserDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterPower;
import com.small.entity.after.AfterUser;
import com.small.mapper.after.AfterUserMapper;
import com.small.service.after.AfterUserService;
import com.small.utils.StringUtil;
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


    @Override
    public JsonResponse resetPassword(AfterUserDto afterUserDto) {
        AfterUser afterUser = new AfterUser();
        afterUser.setId(afterUserDto.getAfterUserId());
        afterUser.setUserPassword(StringUtil.encryptSha256(afterUserDto.getResetPassword()));
        afterUserMapper.updateByPrimaryKeySelective(afterUser);
        return JsonResponse.ok("修改成功！");
    }
}
