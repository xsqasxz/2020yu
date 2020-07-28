package com.small.dto;

/**
 * @author xueshiqi
 * @since 2020/5/11
 */
public class PageDto {
    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 每页多少条数据
     */
    private Integer pageSize;

    public Integer getPageNum() {
        if(pageNum==null || pageNum < 1){
            return 1;
        }else {
            return pageNum;
        }
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        if(pageSize==null || pageSize < 1){
            return 15;
        }else {
            return pageSize;
        }
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
