package com.small.service.front;

import com.github.pagehelper.PageInfo;
import com.small.dto.front.FrontPageDto;
import com.small.entity.JsonResponse;
import com.small.entity.front.FrontHtml;

/**
 * @author xueshiqi
 * @since 2020/10/16
 */
public interface FrontPageService {
    PageInfo<FrontHtml> getFrontHtml(FrontPageDto frontPageDto);

    JsonResponse updateFrontHtml(FrontPageDto frontPageDto);
}
