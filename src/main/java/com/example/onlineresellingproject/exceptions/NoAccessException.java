package com.example.onlineresellingproject.exceptions;

/**
 * Класс {@code NoAccessException} представляет исключение, которое выбрасывается при попытке доступа к ресурсу,
 * к которому у пользователя нет необходимых прав доступа.
 *
 * <p>Это исключение может быть использовано для обработки ситуации, когда пользователь пытается выполнить
 * операцию, к которой у него нет прав доступа. Оно может быть перехвачено и обработано в обработчиках исключений
 * для отправки соответствующего HTTP-ответа клиенту.
 *
 * @see RuntimeException
 */
public class NoAccessException extends RuntimeException {

    /**
     * Конструктор класса {@code NoAccessException} принимает сообщение об ошибке и передает его в конструктор
     * суперкласса {@code RuntimeException}.
     *
     * @param msg сообщение об ошибке
     */
    public NoAccessException(String msg) {
        super(msg);
    }
}
