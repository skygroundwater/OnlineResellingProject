package com.example.onlineresellingproject.exceptions;

public class NotValidModelException extends RuntimeException {

    public NotValidModelException(String txt) {
        super(txt);
    }

    public NotValidModelException() {
        super("Модель сформирована неверно");
    }
}
