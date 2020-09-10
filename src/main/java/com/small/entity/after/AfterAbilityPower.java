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
    private Integer after_ability_id;
    private Integer after_power_id;
}
