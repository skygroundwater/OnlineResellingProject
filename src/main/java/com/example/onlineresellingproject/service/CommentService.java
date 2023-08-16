package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.entity.UserEntity;

public interface CommentService {

    CommentEntity post(CommentEntity model);

    CommentEntity patch(CommentEntity model);

    void delete(Long id);

    CommentEntity get(Long id);

    Comment create(UserEntity user, AdEntity adEntity, CreateOrUpdateComment dto);

    Comment update(Long id, CreateOrUpdateComment dto);

    Comments findCommentsByAdId(Long id);

}
