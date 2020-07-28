package com.small.service.loan;

import com.small.entity.JsonResponse;
import com.small.entity.person.FollowUpComment;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/5/29
 * 外访评论
 */
@Validated
public interface FollowUpCommentService {
    List<FollowUpComment> findByFollowUpCommentList(@NotNull(message = "项目id不可为空") Long projectId);

    JsonResponse addFollowUpComment(@NotNull(message = "项目id不可为空") Long projectId, @Size(max = 500,message = "评论长度不可以超过500") String contactContent);
}
