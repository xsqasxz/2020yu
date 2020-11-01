package com.small.service.blog;

import com.github.pagehelper.PageInfo;
import com.small.dto.blog.BlogDto;
import com.small.dto.blog.BlogNavigationDto;
import com.small.vo.blog.DetailedVo;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/9/16
 * blog导航部分
 */
public interface BlogNavigationService {
    List<BlogNavigationDto> selectByType(Short indexNavigation);

    PageInfo<DetailedVo> getDetailed(BlogDto blogDto);
}
