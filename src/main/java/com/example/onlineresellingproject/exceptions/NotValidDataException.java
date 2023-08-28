package com.example.onlineresellingproject.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotValidDataException extends RuntimeException {

    public NotValidDataException(String msg) {
        super(msg);
    }

}