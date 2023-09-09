package com.example.onlineresellingproject.dto.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Класс, представляющий DTO (Data Transfer Object) для создания или обновления комментария к объявлению.
 * Этот класс наследует функциональность от абстрактного класса {@link CommentDTO}.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateOrUpdateComment extends CommentDTO {

    /**
     * Текст комментария.
     */
    private String text;

}