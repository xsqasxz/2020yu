package com.small.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dicid;

    private Long protypeid;

    private String itemname;

    private String itemvalue;

    private Long sn;

    private String dickey;

    private String status;
    /**是否app需要的，1是，0否*/
    private Boolean isapp;

    private String descp;
}