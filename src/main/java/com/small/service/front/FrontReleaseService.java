package com.small.service.front;

import com.small.dto.front.FrontReleaseDto;
import com.small.entity.JsonResponse;

/**
 * @author xueshiqi
 * @since 2020/10/14
 * 文章发布
 */
public interface FrontReleaseService {
    JsonResponse saveFrontRelease(FrontReleaseDto frontReleaseDto);
}
