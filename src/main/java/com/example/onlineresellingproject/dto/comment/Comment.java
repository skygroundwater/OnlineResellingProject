package com.example.onlineresellingproject.dto.comment;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

}
