package com.small.service.loan.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.constant.RedisUtilsData;
import com.small.dto.loan.PersonPhonecheckDto;
import com.small.entity.JsonResponse;
import com.small.entity.person.PersonPhonecheck;
import com.small.entity.person.PersonRelation;
import com.small.entity.person.PhoneUrgeRecord;
import com.small.entity.project.OverdueProject;
import com.small.mapper.person.PersonPhonecheckMapper;
import com.small.mapper.person.PersonRelationMapper;
import com.small.mapper.person.PhoneUrgeRecordMapper;
import com.small.mapper.project.OverdueProjectMapper;
import com.small.security.entity.SysUser;
import com.small.service.loan.PhoneUrgeRecordService;
import com.small.vo.loan.PersonPhonecheckVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/5/26
 */
@Service
public class PhoneUrgeRecordServiceImpl implements PhoneUrgeRecordService {

    //电催记录（电审）
    @Resource
    private PersonPhonecheckMapper personPhonecheckMapper;
    //电催详情
    @Resource
    private PhoneUrgeRecordMapper phoneUrgeRecordMapper;
    //关系人表
    @Resource
    private PersonRelationMapper personRelationMapper;
    //逾期项目
    @Resource
    private OverdueProjectMapper overdueProjectMapper;

    @Override
    public List<PersonPhonecheck> findByPersonPhonecheckList(Long projectId) {
        PersonPhonecheck personPhonecheck = new PersonPhonecheck();
        personPhonecheck.setProjectid(projectId);
        return personPhonecheckMapper.select(personPhonecheck);
    }

    /**
     * 根据电催id查询电催详情
     * @return
     */
    @Override
    public PersonPhonecheck findByPersonPhonecheck(PersonPhonecheckDto personPhonecheckDto) {
        if(personPhonecheckDto.getPersonPhonecheckId()!=null) {
            return personPhonecheckMapper.selectByPrimaryKey(personPhonecheckDto.getPersonPhonecheckId());
        }else{
            PersonPhonecheck personPhonecheck = new PersonPhonecheck();
            personPhonecheck.setNewsaveflag((short) 2);
            personPhonecheck.setPersonid(personPhonecheckDto.getPersonid());
            personPhonecheck.setProjectid(personPhonecheckDto.getProjectid());
            return personPhonecheck;
        }
    }

    /**
     * 电话核查详情
     * @param personPhonecheckDto
     * @return
     */
    @Override
    public PageInfo<PhoneUrgeRecord> findByPhoneUrgeRecordPage(PersonPhonecheckDto personPhonecheckDto) {
        if(personPhonecheckDto.getPersonPhonecheckId()!=null) {
            PhoneUrgeRecord phoneUrgeRecord = new PhoneUrgeRecord();
            phoneUrgeRecord.setPhoneCheckId(personPhonecheckDto.getPersonPhonecheckId());
            PageHelper.startPage(personPhonecheckDto.getPageNum(), personPhonecheckDto.getPageSize()).setOrderBy(" contact_date desc");
            return new PageInfo<>(phoneUrgeRecordMapper.select(phoneUrgeRecord));
        }else{
            return new PageInfo<>();
        }
    }

    @Override
    public JsonResponse addPhoneUrgeRecord(Long personPhonecheckId, Long projectid,String contactContent) {
        PhoneUrgeRecord phoneUrgeRecord = new PhoneUrgeRecord();
        phoneUrgeRecord.setPhoneCheckId(personPhonecheckId);
        phoneUrgeRecord.setOverdueProjectId(projectid);
        phoneUrgeRecord.setContactDate(new Date());
        phoneUrgeRecord.setContactContent(contactContent);
        Authentication originalUser = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = (SysUser) originalUser.getPrincipal();
//        phoneUrgeRecord.setContacterId(user.getAccountId());
        phoneUrgeRecordMapper.insert(phoneUrgeRecord);
        OverdueProject overdueProject = new OverdueProject();
        overdueProject.setId(projectid);
        overdueProject.setLastFollowDate(new Date());
//        overdueProject.setLastFollowUpUserId(user.getAccountId());
        overdueProjectMapper.updateByPrimaryKeySelective(overdueProject);
        return JsonResponse.ok("添加成功");
    }

    /**
     * 查询电催评论详情
     * @param projectid
     * @return
     */
    @Override
    public List<PersonPhonecheckVo> allPhoneUrgeRecord(Long projectid) {
        PersonPhonecheck personPhonecheck = new PersonPhonecheck();
        personPhonecheck.setProjectid(projectid);
        List<PersonPhonecheck> list = personPhonecheckMapper.select(personPhonecheck);
        List<PersonPhonecheckVo> phonecheckVoList = new ArrayList<>();
        list.forEach(p->{
            PhoneUrgeRecord phoneUrgeRecord = new PhoneUrgeRecord();
            phoneUrgeRecord.setPhoneCheckId(p.getId());
            List<PhoneUrgeRecord> phoneUrgeRecordList= phoneUrgeRecordMapper.select(phoneUrgeRecord);
            if(!phoneUrgeRecordList.isEmpty()) {
                PersonPhonecheckVo personPhonecheckVo = new PersonPhonecheckVo();
                personPhonecheckVo.setRelationship(p.getRelationship());
                personPhonecheckVo.setPhonetype(p.getPhonetype());
                personPhonecheckVo.setRelationname(p.getRelationname());
                personPhonecheckVo.setPhonenumberA(p.getPhonenumberA());
                personPhonecheckVo.setList(phoneUrgeRecordList);
                phonecheckVoList.add(personPhonecheckVo);
            }
        });
        return phonecheckVoList;
    }
}
