package com.small.entity.after;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xueshiqi
 * @since 2020/7/27
 * 后台帐号
 */
@Data
@Entity
@Table(name="after_user")
public class AfterUser implements Serializable {
    /**
     * @Table即数据表表名
     * @Column即列名
     * @Id作为主键，需要注意，@Id注解不可有多个
     * @Transient即冗余字段，不与数据库任何字段对应
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**帐号*/
    private String userName;
    /**密码*/
    private String userPassword;
    /**是否有效*/
    private Boolean userEffective;
    /**默认岗位id*/
    private Integer abilityId;
}
