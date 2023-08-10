package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.exceptions.NotValidDataException;
import com.example.onlineresellingproject.exceptions.NotValidModelException;
import com.example.onlineresellingproject.repository.CommentEntityRepo;
import com.example.onlineresellingproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentEntityRepo repository;

    private final ModelMapper modelMapper;

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
    public CreateOrUpdateComment createOrUpdate(Long key, CreateOrUpdateComment dto) {
        CommentEntity commentEntity = get(key);
        if (commentEntity != null) {
            commentEntity.setText(dto.getText());
            post(commentEntity);
            return dto;
        } else throw new NotFoundInDataBaseException("Комментарий не был найден");
    }

    @Override
    public List<CommentEntity> findCommentsByAdId(Long id) {
        return repository.findAllByAd_Id(id);
    }
}
