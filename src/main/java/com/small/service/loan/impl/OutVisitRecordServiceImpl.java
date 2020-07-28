package com.small.service.loan.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.small.dto.loan.OutVisitRecordDto;
import com.small.entity.JsonResponse;
import com.small.entity.person.OutVisitDetail;
import com.small.entity.person.OutVisitRecord;
import com.small.entity.project.OverdueProject;
import com.small.mapper.person.OutVisitDetailMapper;
import com.small.mapper.person.OutVisitRecordMapper;
import com.small.mapper.project.OverdueProjectMapper;
import com.small.security.entity.SysUser;
import com.small.service.loan.OutVisitRecordService;
import com.small.vo.loan.OutVisitRecordVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/5/26
 */
@Service
public class OutVisitRecordServiceImpl implements OutVisitRecordService {
    @Resource
    private OutVisitRecordMapper outVisitRecordMapper;
    @Resource
    private OutVisitDetailMapper outVisitDetailMapper;
    @Resource
    private OverdueProjectMapper overdueProjectMapper;

    @Override
    public List<OutVisitRecord> findByOutVisitRecordList(Long projectId) {
        OutVisitRecord outVisitRecord = new OutVisitRecord();
        outVisitRecord.setProjectId(projectId);
        return outVisitRecordMapper.select(outVisitRecord);
    }

    /**
     * 查询外访记录
     * @param outVisitRecordDto
     * @return
     */
    @Override
    public OutVisitRecord findByOutVisitRecord(OutVisitRecordDto outVisitRecordDto) {
        if(outVisitRecordDto.getOutVisitRecordId() != null){
            return outVisitRecordMapper.selectByPrimaryKey(outVisitRecordDto.getOutVisitRecordId());
        }else{
            OutVisitRecord outVisitRecord = new OutVisitRecord();
            outVisitRecord.setIsNew(true);
            outVisitRecord.setPersonId(outVisitRecordDto.getPersonId());
            outVisitRecord.setProjectId(outVisitRecordDto.getProjectId());
            return outVisitRecord;
        }
    }

    /**
     * 查询外访评论
     * @param outVisitRecordDto
     * @return
     */
    @Override
    public PageInfo<OutVisitDetail> findByOutVisitDetailPage(OutVisitRecordDto outVisitRecordDto) {
        if(outVisitRecordDto.getOutVisitRecordId() != null){
            OutVisitDetail outVisitDetail = new OutVisitDetail();
            outVisitDetail.setOutVisitRecordId(outVisitRecordDto.getOutVisitRecordId());
            PageHelper.startPage(outVisitRecordDto.getPageNum(), outVisitRecordDto.getPageSize()).setOrderBy(" visit_date desc");
            return new PageInfo<>(outVisitDetailMapper.select(outVisitDetail));
        }else{
            return new PageInfo<>();
        }
    }

    /**
     * 查询全部的外访记录和评论
     * @param projectId
     * @return
     */
    @Override
    public List<OutVisitRecordVo> allOutVisitRecord(Long projectId) {
        OutVisitRecord outVisitRecord = new OutVisitRecord();
        outVisitRecord.setProjectId(projectId);
        List<OutVisitRecord> visitRecordList = outVisitRecordMapper.select(outVisitRecord);
        List<OutVisitRecordVo> list = new ArrayList<>();
        visitRecordList.forEach(o->{
            OutVisitDetail outVisitDetail = new OutVisitDetail();
            outVisitDetail.setOutVisitRecordId(Long.valueOf(o.getId()));
            List<OutVisitDetail> outVisitDetails = outVisitDetailMapper.select(outVisitDetail);
            if(!CollectionUtils.isEmpty(outVisitDetails)) {
                list.add(new OutVisitRecordVo(o.getAddressType(), o.getUnitname(), o.getDetailedAddress(), outVisitDetails));
            }
        });
        return list;
    }

    /**
     * 添加外访记录
     * @param outVisitRecordId
     * @param projectId
     * @param contactContent
     * @return
     */
    @Override
    public JsonResponse addOutVisitDetail(Long outVisitRecordId, Long projectId, String contactContent) {
        OutVisitDetail outVisitDetail = new OutVisitDetail();
        outVisitDetail.setOutVisitRecordId(outVisitRecordId);
        outVisitDetail.setOverdueProjectId(projectId);
        outVisitDetail.setVisitContent(contactContent);
        outVisitDetail.setVisitDate(new Date());
        Authentication originalUser = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = (SysUser) originalUser.getPrincipal();
//        outVisitDetail.setVisitId(user.getAccountId());
        outVisitDetailMapper.insert(outVisitDetail);
        OverdueProject overdueProject = new OverdueProject();
        overdueProject.setId(projectId);
        overdueProject.setAbroadLastFollowDate(new Date());
        overdueProjectMapper.updateByPrimaryKeySelective(overdueProject);
        return JsonResponse.ok("添加成功");
    }

    /**
     * 保存外访地址
     * @param outVisitRecordDto
     * @return
     */
    @Override
    public JsonResponse saveOutVisitRecord(OutVisitRecordDto outVisitRecordDto) {
        OutVisitRecord outVisitRecord = new OutVisitRecord();
        outVisitRecord.setAddressState(outVisitRecordDto.getAddressState());
        outVisitRecord.setAddressType(outVisitRecordDto.getAddressType());
        outVisitRecord.setDetailedAddress(outVisitRecordDto.getDetailedAddress());
        outVisitRecord.setSource(outVisitRecordDto.getSource());
        outVisitRecord.setUnitname(outVisitRecordDto.getUnitname());
        if(outVisitRecordDto.getOutVisitRecordId()!=null){
            outVisitRecord.setId(Math.toIntExact(outVisitRecordDto.getOutVisitRecordId()));
            outVisitRecordMapper.updateByPrimaryKeySelective(outVisitRecord);
        }else {
            outVisitRecord.setIsNew(true);
            outVisitRecord.setProjectId(outVisitRecordDto.getProjectId());
            outVisitRecord.setPersonId(outVisitRecordDto.getPersonId());
            outVisitRecordMapper.insert(outVisitRecord);
        }
        return JsonResponse.okData("添加外访地址成功",outVisitRecord.getId());
    }

}
