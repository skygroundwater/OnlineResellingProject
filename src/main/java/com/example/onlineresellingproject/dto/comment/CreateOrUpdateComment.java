package com.example.onlineresellingproject.dto.comment;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateOrUpdateComment extends CommentDTO{

    private String text;

}
