package com.example.onlineresellingproject.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс, представляющий список комментариев к объявлениям.
 * Содержит информацию о количестве комментариев и самих комментариях.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    /**
     * Количество комментариев в списке.
     */
    private Integer count;

    /**
     * Список комментариев.
     */
    private List<Comment> results;

}
