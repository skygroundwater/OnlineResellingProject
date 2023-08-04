package com.example.onlineresellingproject.exceptions;

import com.example.onlineresellingproject.entity.OnlineResellingProjectAbstractModel;

public class NotValidModelException extends RuntimeException {

    public NotValidModelException(String txt) {
        super(txt);
    }

    public NotValidModelException() {
        super("Модель сформирована неверно");
    }

    public NotValidModelException(Class<? extends OnlineResellingProjectAbstractModel> clazz){
        super("Модель класса " + clazz.getSimpleName() + " сформирована неверно");
    }
}
