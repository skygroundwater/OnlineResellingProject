package com.example.onlineresellingproject.dto.ad;

/**
 * Класс, представляющий данные для создания или обновления объявления.
 * Расширяет абстрактный класс {@link AdDTO}.
 */
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrUpdateAd {

    /**
     * Заголовок объявления.
     */
    private String title;

    /**
     * Цена объявления.
     */
    private Integer price;

    /**
     * Описание объявления.
     */
    private String description;

}
