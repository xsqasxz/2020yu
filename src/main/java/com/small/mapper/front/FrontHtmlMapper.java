package com.small.mapper.front;

import com.small.dto.blog.BlogDto;
import com.small.dto.front.FrontPageDto;
import com.small.entity.front.FrontHtml;
import com.small.vo.blog.DetailedVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/10/14
 * 文章发布
 */
public interface FrontHtmlMapper extends Mapper<FrontHtml> {
    List<FrontHtml> getFrontHtml(FrontPageDto frontPageDto);

    List<DetailedVo> getDetailed(BlogDto blogDto);
}
