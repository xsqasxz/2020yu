package com.small.service.front.impl;

import com.small.dto.front.FrontReleaseDto;
import com.small.entity.JsonResponse;
import com.small.entity.front.FrontHtml;
import com.small.mapper.front.FrontHtmlMapper;
import com.small.service.front.FrontReleaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xueshiqi
 * @since 2020/10/14
 * 文章发布
 */
@Service
public class FrontReleaseServiceImpl implements FrontReleaseService {

    @Resource
    private FrontHtmlMapper frontHtmlMapper;

    @Override
    public JsonResponse saveFrontRelease(FrontReleaseDto frontReleaseDto) {
        FrontHtml frontHtml = new FrontHtml(frontReleaseDto);
        if(frontHtml!=null && frontHtml.getId()!=null){
            frontHtmlMapper.updateByPrimaryKeySelective(frontHtml);
        }else {
            frontHtmlMapper.insert(frontHtml);
        }
        return JsonResponse.ok("模版保存成功！");
    }

    @Override
    public FrontHtml selectByPrimaryKey(FrontReleaseDto frontReleaseDto) {
        return frontHtmlMapper.selectByPrimaryKey(new FrontHtml(frontReleaseDto));
    }

    @Override
    public JsonResponse deleteFrontHtml(FrontReleaseDto frontReleaseDto) {
        frontHtmlMapper.deleteByPrimaryKey(frontReleaseDto.getFrontReleaseId());
        return JsonResponse.ok("模版删除成功！");
    }
}
