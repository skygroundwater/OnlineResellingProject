package com.example.onlineresellingproject.exceptions;

import com.example.onlineresellingproject.entity.ProjectEntity;

public class NotValidModelException extends RuntimeException {

    public NotValidModelException(String txt) {
        super(txt);
    }

    public NotValidModelException() {
        super("Модель сформирована неверно");
    }

    public NotValidModelException(Class<? extends ProjectEntity> clazz){
        super("Модель класса " + clazz.getSimpleName() + " сформирована неверно");
    }
}
