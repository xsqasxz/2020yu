package com.small.entity.after;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="after_ability_power")
public class AfterAbilityPower  implements Serializable {
    /***/
    private Integer afterAbilityId;
    private Integer afterPowerId;
}
