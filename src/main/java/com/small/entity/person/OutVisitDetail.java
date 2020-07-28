package com.small.entity.person;

import com.small.utils.VestaGenId;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

/**
 * 外访记录
 */
@Data
@Entity
@Table(name = "t_out_visit_detail")
public class OutVisitDetail {
    @Id
    @KeySql(genId = VestaGenId.class)
    private Integer id;

    /**外访时间*/
    private Date visitDate;
    /**外访记录*/
    private Long outVisitRecordId;
    /**逾期跟进记录*/
    private Long overdueProjectId;
    /**外访人*/
    private Long visitId;
    /**外访内容*/
    @Lob
    private String visitContent;
}