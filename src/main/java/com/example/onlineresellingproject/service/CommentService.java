package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.entity.CommentEntity;

import java.util.List;

public interface CommentService extends OnlineResellingProjectService<CommentEntity, Long, CreateOrUpdateComment> {

    List<CommentEntity> findCommentsByAdId(Long id);

}
