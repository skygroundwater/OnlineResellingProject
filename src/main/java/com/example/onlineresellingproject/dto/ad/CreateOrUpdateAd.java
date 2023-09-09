package com.example.onlineresellingproject.dto.ad;

/**
 * Класс, представляющий данные для создания или обновления объявления.
 * Расширяет абстрактный класс {@link AdDTO}.
 */
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateOrUpdateAd extends AdDTO {

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
