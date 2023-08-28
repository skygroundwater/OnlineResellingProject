package com.example.onlineresellingproject.exceptions;

public class NotFoundInDataBaseException extends RuntimeException {

    public NotFoundInDataBaseException(String txt) {
        super(txt);
    }

    public NotFoundInDataBaseException() {
        super("Объект не найден в базе данных");
    }
}
