package com.small.service.blog;

import com.small.dto.blog.NavigationDto;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/9/16
 * blog导航部分
 */
public interface NavigationService {
    List<NavigationDto> selectByType(Integer indexNavigation);
}
