package com.example.onlineresellingproject.mappers;

import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.entity.CommentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper implements Mapper {

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

    public Comments mapToComments(List<CommentEntity> entities) {
        return Comments.builder()
                .results(entities.stream().map(this::mapToComment)
                        .collect(Collectors.toList()))
                .count(entities.size())
                .build();
    }
}
