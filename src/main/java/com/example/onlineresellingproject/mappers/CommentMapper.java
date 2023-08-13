package com.example.onlineresellingproject.mappers;

import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper implements Mapper {

    private final ModelMapper modelMapper;

    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public Comment mapToComment(CommentEntity entity) {
        return modelMapper.map(entity, Comment.class);
    }

    public CommentEntity mapToEntity(Comment comment, AdEntity adEntity) {
        if (adEntity != null) {
            return CommentEntity.builder()
                    .ad(adEntity)
                    .text(comment.getText())
                    .user(adEntity.getUser())
                    .createdAt(LocalDateTime.now())
                    .build();
        } else throw new NotFoundInDataBaseException("Объявление не было найдено");
    }

    public Comments mapToComments(List<CommentEntity> entities) {
        return Comments.builder()
                .results(entities.stream().map(this::mapToComment)
                        .collect(Collectors.toList()))
                .count(entities.size())
                .build();
    }
}
