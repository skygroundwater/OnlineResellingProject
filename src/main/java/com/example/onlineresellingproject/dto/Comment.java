package com.example.onlineresellingproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {

    private Integer author;

    private String authorImage;

    private String authorFirstName;

    private Integer createdAt;

    private Integer pk;

    private String text;

}
