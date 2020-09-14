package com.small.service.after.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.constant.AfterState;
import com.small.dto.after.AfterUserDto;
import com.small.entity.JsonResponse;
import com.small.entity.after.AfterUser;
import com.small.mapper.after.AfterUserMapper;
import com.small.security.entity.SysUser;
import com.small.service.after.AfterUserService;
import com.small.utils.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        afterUser.setUserPassword(StringUtil.encryptSha256(afterUserDto.getUserPassword()));
        afterUserMapper.updateByPrimaryKeySelective(afterUser);
        return JsonResponse.ok("修改成功！");
    }

    @Override
    public JsonResponse addAfterUser(AfterUserDto afterUserDto) {
        AfterUser afterUser = new AfterUser();
        afterUser.setUserName(afterUserDto.getUserName());
        if(CollectionUtils.isEmpty(afterUserMapper.select(afterUser))){
            afterUser.setUserPassword(StringUtil.encryptSha256(afterUserDto.getUserPassword()));
            afterUser.setUserEffective(false);
            afterUser.setAbilityId(AfterState.AFTER_ABILITY);
            afterUserMapper.insert(afterUser);
            return JsonResponse.ok("添加成功！");
        }else{
            return JsonResponse.error("帐号已存在！");
        }
    }

    @Override
    public JsonResponse updateAfterUserAbilityId(AfterUserDto afterUserDto) {
        AfterUser afterUser = new AfterUser();
        afterUser.setAbilityId(afterUserDto.getAfterAbilitieId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = (SysUser)authentication.getPrincipal();
        afterUser.setId(user.getUserId());
        afterUserMapper.updateByPrimaryKeySelective(afterUser);
        return JsonResponse.ok("修改成功！");
    }
}
