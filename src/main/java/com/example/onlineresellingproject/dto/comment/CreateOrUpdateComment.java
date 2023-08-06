package com.example.onlineresellingproject.dto.comment;

import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.entity.CommentEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateOrUpdateComment extends CommentDTO {

    private String text;

    public static CreateOrUpdateComment map(CommentEntity entity, ModelMapper modelMapper) {
        return modelMapper.map(entity, CreateOrUpdateComment.class);
    }
}