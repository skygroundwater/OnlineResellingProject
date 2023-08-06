package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.repository.CommentEntityRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends AbstractService<CommentEntity, Long, CommentEntityRepo, CreateOrUpdateComment> implements com.example.onlineresellingproject.service.CommentService {
    CommentServiceImpl(CommentEntityRepo repository,
                       ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public CreateOrUpdateComment createOrUpdate(Long key, CreateOrUpdateComment dto) {
        CommentEntity commentEntity = get(key);
        if (commentEntity != null) {
            commentEntity.setText(dto.getText());
            return CreateOrUpdateComment.map(post(commentEntity), getModelMapper());
        } else throw new NotFoundInDataBaseException("Комментарий не был найден");
    }

    @Override
    public List<CommentEntity> findCommentsByAdId(Long id) {
        return getRepository().findAllByAd_Id(id);
    }
}
