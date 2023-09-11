package com.example.onlineresellingproject.dto.comment;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * Класс, представляющий комментарий к объявлению.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Comment {

    /**
     * Уникальный идентификатор автора комментария.
     */
    private Long author;

    /**
     * Ссылка на изображение автора комментария.
     */
    private String authorImage;

    /**
     * Имя автора комментария.
     */
    private String authorFirstName;

    /**
     * Время создания комментария.
     */
    private LocalDateTime createdAt;

    /**
     * Уникальный идентификатор комментария.
     */
    private Long pk;

    /**
     * Текст комментария.
     */
    private String text;

}
