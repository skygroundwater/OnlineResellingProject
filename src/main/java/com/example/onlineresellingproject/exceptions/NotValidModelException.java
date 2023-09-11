package com.example.onlineresellingproject.exceptions;

/**
 * Класс {@code NotValidModelException} представляет исключение, которое выбрасывается при попытке
 * использования модели, которая не соответствует ожидаемой структуре или не прошла проверку валидации.
 *
 * <p>Это исключение может быть использовано для обработки ситуаций, когда модель данных считается недействительной
 * или некорректной и не может быть обработана в приложении. Оно может быть перехвачено и обработано в обработчиках
 * исключений для отправки соответствующего HTTP-ответа клиенту.
 *
 * @see RuntimeException
 */
public class NotValidModelException extends RuntimeException {

    /**
     * Конструктор класса {@code NotValidModelException} принимает сообщение об ошибке и передает его в конструктор
     * суперкласса {@code RuntimeException}.
     *
     * @param txt сообщение об ошибке
     */
    public NotValidModelException(String txt) {
        super(txt);
    }

    /**
     * Конструктор класса {@code NotValidModelException} создает исключение с предопределенным сообщением об ошибке.
     * Это сообщение указывает, что модель сформирована неверно.
     */
    public NotValidModelException() {
        super("Модель сформирована неверно");
    }
}
