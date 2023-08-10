package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.entity.CommentEntity;

import java.util.List;

public interface CommentService {

    CommentEntity post(CommentEntity model);

    CommentEntity patch(CommentEntity model);

    void delete(Long id);

    CommentEntity get(Long id);

    CreateOrUpdateComment createOrUpdate(Long key, CreateOrUpdateComment dto);

    List<CommentEntity> findCommentsByAdId(Long id);

}
