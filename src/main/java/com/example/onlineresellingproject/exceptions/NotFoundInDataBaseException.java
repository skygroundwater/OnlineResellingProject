package com.example.onlineresellingproject.exceptions;

/**
 * Класс {@code NotFoundInDataBaseException} представляет исключение, которое выбрасывается при попытке
 * найти объект в базе данных, но объект не был обнаружен.
 *
 * <p>Это исключение может быть использовано для обработки ситуации, когда требуемый объект не найден
 * в базе данных. Оно может быть перехвачено и обработано в обработчиках исключений для отправки соответствующего
 * HTTP-ответа клиенту.
 *
 * @see RuntimeException
 */
public class NotFoundInDataBaseException extends RuntimeException {

    /**
     * Конструктор класса {@code NotFoundInDataBaseException} принимает сообщение об ошибке и передает его в конструктор
     * суперкласса {@code RuntimeException}.
     *
     * @param txt сообщение об ошибке
     */
    public NotFoundInDataBaseException(String txt) {
        super(txt);
    }

    /**
     * Конструктор класса {@code NotFoundInDataBaseException} создает исключение с сообщением по умолчанию,
     * указывающим, что объект не был найден в базе данных.
     */
    public NotFoundInDataBaseException() {
        super("Объект не найден в базе данных");
    }
}
