package com.example.onlineresellingproject.mappers;

import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.entity.CommentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс {@code CommentMapper} представляет собой компонент, отвечающий за преобразование сущностей и DTO
 * (Data Transfer Objects), связанных с комментариями (Comment), в различные форматы и обратно.
 *
 * <p>Этот класс реализует интерфейс {@code Mapper}, который определяет методы для преобразования данных.
 * В частности, этот класс выполняет преобразование сущностей {@code CommentEntity} в DTO {@code Comment} и {@code Comments}.
 *
 * @see Mapper
 * @see Comment
 * @see Comments
 * @see CommentEntity
 */
@Component
public class CommentMapper implements Mapper {

    /**
     * Метод {@code mapToComment} выполняет преобразование сущности комментария {@code CommentEntity} в DTO комментария {@code Comment}.
     *
     * @param entity сущность комментария
     * @return объект DTO комментария
     */
    public Comment mapToComment(CommentEntity entity) {
        Comment comment = new Comment();
        comment.setPk(entity.getId());
        comment.setAuthor(entity.getUser().getId());
        comment.setAuthorImage(entity.getUser().getImage());
        comment.setText(entity.getText());
        comment.setAuthorFirstName(entity.getUser().getFirstName());
        comment.setCreatedAt(entity.getCreatedAt());
        return comment;
    }

    /**
     * Метод {@code mapToComments} выполняет преобразование списка сущностей комментариев {@code CommentEntity}
     * в DTO комментариев {@code Comments}, включая количество комментариев и сами комментарии.
     *
     * @param entities список сущностей комментариев
     * @return объект DTO комментариев
     */
    public Comments mapToComments(List<CommentEntity> entities) {
        return Comments.builder()
                .results(entities
                        .stream().map(this::mapToComment)
                        .collect(Collectors.toList()))
                .count(entities.size())
                .build();
    }
}
