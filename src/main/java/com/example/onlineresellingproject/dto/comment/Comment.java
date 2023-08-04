package com.example.onlineresellingproject.dto.comment;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Comment extends CommentDTO{

    private Long author;

    private String authorImage;

    private String authorFirstName;

    private Integer createdAt;

    private Long pk;

    private String text;

}
