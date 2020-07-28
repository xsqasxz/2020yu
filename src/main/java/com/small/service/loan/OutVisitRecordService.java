package com.small.service.loan;

import com.github.pagehelper.PageInfo;
import com.small.dto.loan.OutVisitRecordDto;
import com.small.entity.JsonResponse;
import com.small.entity.person.OutVisitDetail;
import com.small.entity.person.OutVisitRecord;
import com.small.vo.loan.OutVisitRecordVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/5/26
 * 外访地址
 */
@Validated
public interface OutVisitRecordService {

    List<OutVisitRecord> findByOutVisitRecordList(@NotNull(message = "项目id不可为空") Long projectId);

    OutVisitRecord findByOutVisitRecord(OutVisitRecordDto outVisitRecordDto);

    PageInfo<OutVisitDetail> findByOutVisitDetailPage(OutVisitRecordDto outVisitRecordDto);

    List<OutVisitRecordVo> allOutVisitRecord(@NotNull(message = "项目id不可为空") Long projectId);

    JsonResponse addOutVisitDetail(@NotNull(message = "地址id不可为空") Long outVisitRecordId, @NotNull(message = "项目id不可为空") Long projectId,@NotNull(message = "外访内容不可以为空") @Size(max =250,message = "外访内容250以内") String contactContent);

    JsonResponse saveOutVisitRecord(OutVisitRecordDto outVisitRecordDto);
}
