package com.example.onlineresellingproject.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс {@code ErrorResponse} представляет объект, используемый для передачи сообщения об ошибке в ответе на HTTP-запрос.
 * Он содержит сообщение об ошибке и временную метку.
 *
 * <p>Этот класс может быть использован для структурированной передачи информации об ошибках при обработке HTTP-запросов
 * и может быть сериализован в JSON, чтобы вернуть клиенту информацию об ошибке.
 *
 * @see lombok.AllArgsConstructor
 * @see lombok.Getter
 * @see lombok.NoArgsConstructor
 * @see lombok.Setter
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    /**
     * Сообщение об ошибке.
     */
    private String message;

    /**
     * Временная метка, представляющая момент времени, когда произошла ошибка.
     */
    private long timestamp;

}
