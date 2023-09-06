package com.example.onlineresellingproject.dto.comment;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Comment {

    private Long author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Long pk;
    private String text;

}
