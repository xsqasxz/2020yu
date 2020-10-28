package com.small.service.front;

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

    JsonResponse deleteFrontHtml(FrontReleaseDto frontReleaseDto);
}
