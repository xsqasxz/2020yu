package com.small.entity.blog;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xueshiqi
 * @since 2020/7/27
 * 顶部导航
 */
@Data
@Entity
@Table(name="blog_navigation")
public class BlogNavigation implements Serializable  {
    /**
     * @Table即数据表表名
     * @Column即列名
     * @Id作为主键，需要注意，@Id注解不可有多个
     * @Transient即冗余字段，不与数据库任何字段对应
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**导航名称*/
    private String navigationName;
    /**导航功能地址*/
    private String navigationUrl;
    /**0：首页导航*/
    private Short navigationType;
    /**父节点id*/
    private Integer navigationParentNode;
    /**序号*/
    private Integer navigationSerialNumber;
}
