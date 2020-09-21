package com.small.dto.blog;

import lombok.Data;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/9/16
 * blog导航部分
 */
@Data
public class BlogNavigationDto {
    private Integer id;
    /**导航名称*/
    private String navigationName;
    /**导航功能地址*/
    private String navigationUrl;
    /**序号*/
    private Integer navigationSerialNumber;
    /**导航子菜单*/
    private List<BlogNavigationDto> list;

    public BlogNavigationDto() {
    }

    public BlogNavigationDto(Integer id,String navigationName, String navigationUrl, List<BlogNavigationDto> list,Integer navigationSerialNumber) {
        this.id = id;
        this.navigationName = navigationName;
        this.navigationUrl = navigationUrl;
        this.list = list;
        this.navigationSerialNumber = navigationSerialNumber;
    }
}
