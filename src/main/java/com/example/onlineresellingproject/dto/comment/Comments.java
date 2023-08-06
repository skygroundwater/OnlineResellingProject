package com.example.onlineresellingproject.dto.comment;

import com.example.onlineresellingproject.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    private Integer count;

    private List<Comment> comments;

    public static Comments mapToDTO(List<CommentEntity> entities, ModelMapper modelMapper) {
        return Comments.builder()
                .comments(entities.stream().map(commentEntity -> Comment.mapToDTO(commentEntity, modelMapper)).collect(Collectors.toList()))
                .count(entities.size())
                .build();
    }

}
