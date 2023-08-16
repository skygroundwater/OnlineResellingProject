package com.example.onlineresellingproject.dto.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateOrUpdateComment extends CommentDTO {

    private String text;

}