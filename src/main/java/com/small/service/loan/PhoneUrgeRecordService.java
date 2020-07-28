package com.small.service.loan;

import com.github.pagehelper.PageInfo;
import com.small.dto.loan.PersonPhonecheckDto;
import com.small.entity.JsonResponse;
import com.small.entity.person.PersonPhonecheck;
import com.small.entity.person.PhoneUrgeRecord;
import com.small.vo.loan.PersonPhonecheckVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/5/26
 * 电催记录
 */
@Validated
public interface PhoneUrgeRecordService {
    List<PersonPhonecheck> findByPersonPhonecheckList(@NotNull(message = "项目id不可为空") Long projectId);

    PersonPhonecheck findByPersonPhonecheck(PersonPhonecheckDto personPhonecheckDto);

    PageInfo<PhoneUrgeRecord> findByPhoneUrgeRecordPage(PersonPhonecheckDto personPhonecheckDto);

    JsonResponse addPhoneUrgeRecord(@NotNull(message = "请先保存跟进客户信息") Long personPhonecheckId,@NotNull(message = "项目id不可为空") Long projectid,@NotNull(message = "跟进内容不可以为空") @Size(max =250,message = "跟进内容250以内") String contactContent);

    List<PersonPhonecheckVo> allPhoneUrgeRecord(@NotNull(message = "项目id不可为空") Long projectid);
}
