package com.small.vo.loan;

import com.small.entity.person.OutVisitDetail;
import lombok.Data;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/5/27
 */
@Data
public class OutVisitRecordVo {
    /**地址类型CURRENT_ADDRESS(现居住地详址):0;HOUSE_ADDRESS(房产详址):1;" +
     "UNIT_ADDRESS(单位详址):2;BUSINESS_ADDRESS(经营地详址):3;SPOUSE_ADDRESS(配偶单位详址):4;" +
     "RELATION_PERSON_ADDRESS(联系人家庭住址):5;HUJI_ADDRESS(户籍住址):6','QITA_ADDRESS', '其他资料','ZQ_SQB:展期申请表','HK_CRS:还款承诺书'*/
    private Byte addressType;
    /**外访单位*/
    private String unitname;
    /**详细地址*/
    private String detailedAddress;
    /**
     * 评论集合
     */
    private List<OutVisitDetail> list;

    public OutVisitRecordVo(Byte addressType, String unitname, String detailedAddress, List<OutVisitDetail> list) {
        this.addressType = addressType;
        this.unitname = unitname;
        this.detailedAddress = detailedAddress;
        this.list = list;
    }
}
