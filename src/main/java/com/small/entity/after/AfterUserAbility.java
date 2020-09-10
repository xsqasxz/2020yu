package com.small.entity.after;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户对应的岗位表
 */
@Data
@Entity
@Table(name="after_user_ability")
public class AfterUserAbility  implements Serializable {
    /**用户表id*/
    private Integer afterUserId;
    /**岗位表id*/
    private Integer afterAbilityId;
}
