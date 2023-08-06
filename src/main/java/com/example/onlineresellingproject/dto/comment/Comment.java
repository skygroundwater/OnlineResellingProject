package com.example.onlineresellingproject.dto.comment;

import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.CommentEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Comment extends CommentDTO {

    private Long id;

    private Long userId;

    private String userImage;

    private String userFirstName;

    private LocalDateTime createdAt;

    private String text;

    public static Comment mapToDTO(CommentEntity entity, ModelMapper modelMapper) {
        return modelMapper.map(entity, Comment.class);
    }

    public static CommentEntity mapToEntity(Comment comment, AdEntity adEntity){
        return CommentEntity.builder()
                .ad(adEntity)
                .text(comment.getText())
                .user(adEntity.getUser())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
