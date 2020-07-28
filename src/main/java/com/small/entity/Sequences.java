package com.small.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "st_sequences")
public class Sequences {
    private String sequenceName;

    private Integer sequenceNextHiValue;
}