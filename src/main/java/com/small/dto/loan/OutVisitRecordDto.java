package com.small.dto.loan;

import com.small.dto.PageDto;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xueshiqi
 * @since 2020/5/26
 */
@Data
public class OutVisitRecordDto extends PageDto {
    private Long outVisitRecordId;
    @NotNull(message = "项目id不可为空")
    private Long projectId;
    @NotNull(message = "客户id不可为空")
    private Integer personId;
    /**地址类型CURRENT_ADDRESS(现居住地详址):0;HOUSE_ADDRESS(房产详址):1;" +
     "UNIT_ADDRESS(单位详址):2;BUSINESS_ADDRESS(经营地详址):3;SPOUSE_ADDRESS(配偶单位详址):4;" +
     "RELATION_PERSON_ADDRESS(联系人家庭住址):5;HUJI_ADDRESS(户籍住址):6','QITA_ADDRESS', '其他资料','ZQ_SQB:展期申请表','HK_CRS:还款承诺书'*/
    @NotNull(message = "地址类型不可为空")
    @Min(value = 0,message = "请选择正确的地址类型")
    @Max(value = 9,message = "请选择正确的地址类型")
    private Byte addressType;
    /**地址状态 UNCERTAIN_STATE(未确定):0;VALID_STATU(有效地址):1;INVALID_STATE(无效地址):2*/
    @NotNull(message = "地址状态不可为空")
    @Min(value = 0,message = "请选择正确的地址状态")
    @Max(value = 2,message = "请选择正确的地址状态")
    private Byte addressState;
    /**详细地址*/
    @NotNull(message = "详细地址不可为空")
    @Size(min = 5,max = 250,message = "地址详情必须在5-250之间")
    private String detailedAddress;
    /**来源*/
    private String source;
    /**外访单位*/
    private String unitname;
}
