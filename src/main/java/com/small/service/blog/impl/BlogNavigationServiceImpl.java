package com.small.service.blog.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.constant.RedisNameState;
import com.small.dto.blog.BlogDto;
import com.small.dto.blog.BlogNavigationDto;
import com.small.entity.blog.BlogNavigation;
import com.small.mapper.blog.BlogNavigationMapper;
import com.small.mapper.front.FrontHtmlMapper;
import com.small.service.blog.BlogNavigationService;
import com.small.vo.blog.DetailedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author xueshiqi
 * @since 2020/9/16
 * blog导航部分
 */
@Service
public class BlogNavigationServiceImpl implements BlogNavigationService {

    @Resource
    private BlogNavigationMapper blogNavigationMapper;
    @Resource
    private FrontHtmlMapper frontHtmlMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<BlogNavigationDto> selectByType(Short indexNavigation) {
        //查询出全部该类型的数据
        BlogNavigation blogNavigation = new BlogNavigation();
        blogNavigation.setNavigationType(indexNavigation);
        List<BlogNavigation> list = blogNavigationMapper.select(blogNavigation);
        List<BlogNavigationDto> redisBlogNavigationList = redisTemplate.opsForList().range(RedisNameState.blogNavigationDtoList, 0, -1);
        if (CollectionUtils.isEmpty(redisBlogNavigationList)) {
            List<BlogNavigationDto> blogNavigationDtos = new CopyOnWriteArrayList<>();
            Map<Integer,List<BlogNavigationDto>> map = new ConcurrentHashMap<>();
            list.parallelStream().forEach(b->{
                if(b.getNavigationParentNode() == null) {
                    blogNavigationDtos.add(new BlogNavigationDto(b.getId(),b.getNavigationName(), b.getNavigationUrl(), null,b.getNavigationSerialNumber()));
                }else{
                    List<BlogNavigationDto> blogNavigationDtoList;
                    if(map.get(b.getNavigationParentNode())==null){
                        blogNavigationDtoList = new CopyOnWriteArrayList<>();
                    }else{
                        blogNavigationDtoList = map.get(b.getNavigationParentNode());
                    }
                    blogNavigationDtoList.add(new BlogNavigationDto(b.getId(),b.getNavigationName(), b.getNavigationUrl(), null,b.getNavigationSerialNumber()));
                    //排序
                    map.put(b.getNavigationParentNode(),blogNavigationDtoList.stream().sorted(Comparator.comparing(BlogNavigationDto::getNavigationSerialNumber)).collect(Collectors.toList()));
                }
            });
            blogNavigationDtos.parallelStream().forEach(b-> b.setList(map.get(b.getId())));
            redisTemplate.opsForList().rightPushAll(RedisNameState.blogNavigationDtoList, blogNavigationDtos);
            redisBlogNavigationList = blogNavigationDtos.stream().sorted(Comparator.comparing(BlogNavigationDto::getNavigationSerialNumber)).collect(Collectors.toList());
        }
        return redisBlogNavigationList;
    }

    @Override
    public PageInfo<DetailedVo> getDetailed(BlogDto blogDto) {
        PageHelper.startPage(blogDto.getPageNum(), blogDto.getPageSize());
        return new PageInfo<>(frontHtmlMapper.getDetailed(blogDto));
    }
}
