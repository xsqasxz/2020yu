package com.small.service.blog;

import com.small.dto.blog.BlogNavigationDto;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/9/16
 * blog导航部分
 */
public interface BlogNavigationService {
    List<BlogNavigationDto> selectByType(Short indexNavigation);
}
