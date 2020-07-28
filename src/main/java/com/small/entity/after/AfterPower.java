package com.small.entity.after;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xueshiqi
 * @since 2020/7/27
 * 权限记录
 */
@Data
@Entity
@Table(name="after_power")
public class AfterPower implements GrantedAuthority,Serializable  {
    /**
     * @Table即数据表表名
     * @Column即列名
     * @Id作为主键，需要注意，@Id注解不可有多个
     * @Transient即冗余字段，不与数据库任何字段对应
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**权限名称*/
    @Column(name = "power_name")
    private String authority;
    /**别名*/
    private String powerAlias;
    /**级别*/
    private Integer powerLevel;
    /**对应的url地址*/
    private String powerUrl;




}
