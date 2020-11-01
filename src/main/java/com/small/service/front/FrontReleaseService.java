package com.small.service.front;

import com.small.dto.blog.BlogDto;
import com.small.dto.front.FrontReleaseDto;
import com.small.entity.JsonResponse;
import com.small.entity.front.FrontHtml;

/**
 * @author xueshiqi
 * @since 2020/10/14
 * 文章发布
 */
public interface FrontReleaseService {
    JsonResponse saveFrontRelease(FrontReleaseDto frontReleaseDto);

    FrontHtml selectByPrimaryKey(FrontReleaseDto frontReleaseDto);

    FrontHtml selectByPrimaryKey(BlogDto blogDto);

    JsonResponse deleteFrontHtml(FrontReleaseDto frontReleaseDto);

    JsonResponse crdateHomeHtml();

    JsonResponse crdateDetailedHtml();

    JsonResponse crdatedetailsHtml();

    JsonResponse selectIdByPrimaryKey(Integer id, String prev);
}
