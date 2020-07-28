package com.small.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class MyTest {
    @Id
    private Integer id;

    private String name;

    private String valuestring;

    private Date valuedate;
}