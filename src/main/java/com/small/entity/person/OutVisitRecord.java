package com.small.entity.person;

import com.small.utils.VestaGenId;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 外访地址
 */
@Data
@Entity
@Table(name = "t_out_visit_record")
public class OutVisitRecord {
    @Id
    @KeySql(genId = VestaGenId.class)
    private Integer id;
    /**地址类型CURRENT_ADDRESS(现居住地详址):0;HOUSE_ADDRESS(房产详址):1;" +
     "UNIT_ADDRESS(房产详址):2;BUSINESS_ADDRESS(经营地详址):3;SPOUSE_ADDRESS(配偶单位详址):4;" +
     "RELATION_PERSON_ADDRESS(联系人家庭住址):5;HUJI_ADDRESS(户籍住址):6','QITA_ADDRESS', '其他资料','ZQ_SQB:展期申请表','HK_CRS:还款承诺书'*/
    private Byte addressType;
    /**地址状态 UNCERTAIN_STATE(未确定):0;VALID_STATU(有效地址):1;INVALID_STATE(无效地址):2*/
    private Byte addressState;
    /**详细地址*/
    private String detailedAddress;

    private Integer personId;
    /**是否是在外访新增*/
    private Boolean isNew;
    /**来源*/
    private String source;
    /**联系人*/
    private Long projectId;
    /**外访单位*/
    private String unitname;
}