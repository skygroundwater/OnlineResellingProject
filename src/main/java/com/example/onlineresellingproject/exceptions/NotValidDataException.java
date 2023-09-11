package com.example.onlineresellingproject.exceptions;

import lombok.NoArgsConstructor;

/**
 * Класс {@code NotValidDataException} представляет исключение, которое выбрасывается при попытке
 * обработки данных, которые не соответствуют ожидаемому формату или правилам валидации.
 *
 * <p>Это исключение может быть использовано для обработки ситуаций, когда входные данные не являются
 * валидными и не могут быть обработаны в приложении. Оно может быть перехвачено и обработано в обработчиках
 * исключений для отправки соответствующего HTTP-ответа клиенту.
 *
 * @see RuntimeException
 */
@NoArgsConstructor
public class NotValidDataException extends RuntimeException {

    /**
     * Конструктор класса {@code NotValidDataException} принимает сообщение об ошибке и передает его в конструктор
     * суперкласса {@code RuntimeException}.
     *
     * @param msg сообщение об ошибке
     */
    public NotValidDataException(String msg) {
        super(msg);
    }

}