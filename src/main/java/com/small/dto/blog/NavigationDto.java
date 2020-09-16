package com.small.dto.blog;

import lombok.Data;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/9/16
 * blog导航部分
 */
@Data
public class NavigationDto {
    /**导航名称*/
    private String name;
    /**导航url地址*/
    private String urlString;
    /**导航子菜单*/
    private List<NavigationDto> list;
}
