package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.exceptions.NotValidDataException;
import com.example.onlineresellingproject.exceptions.NotValidModelException;
import com.example.onlineresellingproject.mappers.CommentMapper;
import com.example.onlineresellingproject.repository.CommentEntityRepo;
import com.example.onlineresellingproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentEntityRepo repository;

    private final CommentMapper commentMapper;

    @Override
    public final CommentEntity post(CommentEntity model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    @Override
    public final CommentEntity patch(CommentEntity model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    @Override
    public final void delete(Long id) {
        if (id != null) {
            repository.deleteById(id);
        } else throw new NotValidDataException();
    }

    @Override
    public final CommentEntity get(Long id) {
        if (id != null) {
            return repository.findById(id).orElseThrow(NotFoundInDataBaseException::new);
        } else throw new NotValidDataException();
    }

    @Override
    public Comment create(UserEntity user, AdEntity adEntity, CreateOrUpdateComment createOrUpdateComment) {
        return commentMapper.mapToComment(
                post(
                        new CommentEntity().setFieldsAndReturnEntity(user, adEntity, createOrUpdateComment)));
    }

    @Override
    public Comment update(Long id, CreateOrUpdateComment dto) {
        return commentMapper.mapToComment(
                patch(
                        get(id).setFieldsAndReturnEntity(dto)));
    }

    @Override
    public Comments findCommentsByAdId(Long id) {
        return commentMapper.mapToComments(
                repository.findAllByAd_Id(id));
    }
}
