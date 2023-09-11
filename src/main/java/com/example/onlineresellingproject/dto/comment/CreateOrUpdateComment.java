package com.example.onlineresellingproject.dto.comment;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * Класс, представляющий DTO (Data Transfer Object) для создания или обновления комментария к объявлению.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateOrUpdateComment {

    /**
     * Текст комментария.
     */
    private String text;

}
