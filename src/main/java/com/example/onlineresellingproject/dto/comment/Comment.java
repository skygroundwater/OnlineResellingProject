package com.example.onlineresellingproject.dto.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Класс, представляющий комментарий к объявлению.
 * Расширяет абстрактный класс {@link CommentDTO}.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Comment extends CommentDTO {

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
