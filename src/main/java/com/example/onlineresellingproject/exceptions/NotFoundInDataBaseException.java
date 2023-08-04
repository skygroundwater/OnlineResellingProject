package com.example.onlineresellingproject.exceptions;

import com.example.onlineresellingproject.entity.OnlineResellingProjectAbstractModel;

public class NotFoundInDataBaseException extends RuntimeException {

    public NotFoundInDataBaseException(String txt) {
        super(txt);
    }

    public NotFoundInDataBaseException() {
        super("Объект не найден в базе данных");
    }

    public NotFoundInDataBaseException(OnlineResellingProjectAbstractModel model){
        super("Модель класса " + model.getClass().getSimpleName() + " не найдена в базе");
    }

}
