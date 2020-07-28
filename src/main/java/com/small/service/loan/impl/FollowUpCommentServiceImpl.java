package com.small.service.loan.impl;

import com.small.entity.JsonResponse;
import com.small.entity.person.FollowUpComment;
import com.small.mapper.person.FollowUpCommentMapper;
import com.small.security.entity.SysUser;
import com.small.service.loan.FollowUpCommentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/5/29
 * 外访评论
 */
@Service
public class FollowUpCommentServiceImpl implements FollowUpCommentService {

    @Resource
    private FollowUpCommentMapper followUpCommentMapper;

    @Override
    public List<FollowUpComment> findByFollowUpCommentList(Long projectId) {
        FollowUpComment followUpComment = new FollowUpComment();
        followUpComment.setOverdueProjectId(projectId);
        return followUpCommentMapper.select(followUpComment);
    }

    @Override
    public JsonResponse addFollowUpComment(Long projectId,String contactContent) {
        FollowUpComment followUpComment = new FollowUpComment();
        followUpComment.setOverdueProjectId(projectId);
        followUpComment.setCommentContent(contactContent);
        followUpComment.setCommentDate(new Date());
        Authentication originalUser = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = (SysUser) originalUser.getPrincipal();
//        followUpComment.setCommentManId(user.getAccountId());
        followUpCommentMapper.insert(followUpComment);
        return JsonResponse.ok("添加成功");
    }
}
