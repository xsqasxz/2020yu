package com.small.service.front.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.dto.front.FrontPageDto;
import com.small.entity.JsonResponse;
import com.small.entity.front.FrontHtml;
import com.small.mapper.front.FrontHtmlMapper;
import com.small.service.front.FrontPageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xueshiqi
 * @since 2020/10/16
 */
@Service
public class FrontPageServiceImpl implements FrontPageService {
    @Resource
    private FrontHtmlMapper frontHtmlMapper;

    @Override
    public PageInfo<FrontHtml> getFrontHtml(FrontPageDto frontPageDto) {
        PageHelper.startPage(frontPageDto.getPageNum(), frontPageDto.getPageSize());
        return new PageInfo<>(frontHtmlMapper.getFrontHtml(frontPageDto));
    }

    @Override
    public JsonResponse updateFrontHtml(FrontPageDto frontPageDto) {
        frontHtmlMapper.updateByPrimaryKeySelective(new FrontHtml(frontPageDto));
        return JsonResponse.ok("修改成功！");
    }
}
