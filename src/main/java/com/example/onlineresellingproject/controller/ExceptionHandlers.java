package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Класс-обработчик исключений для обработки различных исключительных ситуаций
 * в приложении.
 */
@RestControllerAdvice
public class ExceptionHandlers {

    /**
     * Обработчик исключения {@link NotValidDataException}.
     *
     * @param e Исключение {@link NotValidDataException}, содержащее информацию об ошибке.
     * @return Ответ с сообщением об ошибке и кодом состояния HTTP 400 (Bad Request).
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(NotValidDataException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик исключения {@link NotFoundInDataBaseException}.
     *
     * @param e Исключение {@link NotFoundInDataBaseException}, содержащее информацию об ошибке.
     * @return Ответ с сообщением об ошибке и кодом состояния HTTP 400 (Bad Request).
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(NotFoundInDataBaseException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик исключения {@link NotValidModelException}.
     *
     * @param e Исключение {@link NotValidModelException}, содержащее информацию об ошибке.
     * @return Ответ с сообщением об ошибке и кодом состояния HTTP 400 (Bad Request).
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(NotValidModelException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик исключения {@link NoAccessException}.
     *
     * @param e Исключение {@link NoAccessException}, содержащее информацию об ошибке.
     * @return Ответ с сообщением об ошибке и кодом состояния HTTP 401 (Unauthorized).
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(NoAccessException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.UNAUTHORIZED);
    }
}
