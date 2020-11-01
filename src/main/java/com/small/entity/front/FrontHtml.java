package com.small.entity.front;

import com.small.dto.blog.BlogDto;
import com.small.dto.front.FrontPageDto;
import com.small.dto.front.FrontReleaseDto;
import com.small.security.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xueshiqi
 * @since 2020/10/14
 * 文章发布
 */
@Data
@Entity
@Table(name="front_html")
public class FrontHtml implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**模版名称*/
    private String htmlName;
    /**0：模版 1：首页模版 2：详情模版*/
    private Integer htmlType;
    /**模版关键字，用于内联项目*/
    private String htmlKeyword;
    /**HTML的内容*/
    private String htmlText;
    /**模版创建人*/
    private Integer userId;
    /**是否生效*/
    private Boolean takeEffect;
    /**是否更新*/
    private Boolean wantUpdate;
    /**模版生成的url地址 类型为详情模版时专用*/
    private String htmlUrl;
    /**创建时间*/
    private Date createDate;
    /**修改时间*/
    private Date updateDate;

    public FrontHtml() {
    }

    public FrontHtml(Integer id) {
        this.id = id;
    }

    public FrontHtml(FrontReleaseDto frontReleaseDto) {
        this.id = frontReleaseDto.getFrontReleaseId();
        this.htmlName = frontReleaseDto.getHtmlName();
        this.htmlType = frontReleaseDto.getHtmlType();
        this.htmlKeyword = frontReleaseDto.getHtmlKeyword();
        this.htmlText = frontReleaseDto.getHtmlText();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = (SysUser)authentication.getPrincipal();
        this.userId = user.getUserId();
        this.takeEffect = frontReleaseDto.getTakeEffect()==null?false:frontReleaseDto.getTakeEffect();
        this.wantUpdate = true;
        this.htmlUrl = frontReleaseDto.getHtmlUrl();
        this.createDate = new Date();
        if(this.id!=null) {
            this.updateDate = new Date();
        }
    }

    public FrontHtml(FrontPageDto frontPageDto) {
        this.id = frontPageDto.getFrontHtmlId();
        this.htmlName = frontPageDto.getHtmlName();
        this.htmlType = frontPageDto.getHtmlType();
        this.htmlKeyword = frontPageDto.getHtmlKeyword();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = (SysUser)authentication.getPrincipal();
        this.userId = user.getUserId();
        this.takeEffect = frontPageDto.getTakeEffect()==null?false:frontPageDto.getTakeEffect();
        this.htmlUrl = frontPageDto.getHtmlUrl();
        if(this.id!=null) {
            this.updateDate = new Date();
        }
    }

    public FrontHtml(BlogDto blogDto) {
        this.id = blogDto.getFrontHtmlId();
        this.htmlKeyword = blogDto.getHtmlKeyword();
    }

    public FrontHtml(Integer id, Boolean wantUpdate) {
        this.id = id;
        this.wantUpdate = wantUpdate;
    }
}
