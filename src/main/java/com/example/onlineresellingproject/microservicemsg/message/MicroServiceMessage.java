package com.example.onlineresellingproject.microservicemsg.message;

import java.io.Serializable;

/**
 * Интерфейс MicroServiceMessage представляет сообщение, которое может быть отправлено через микросервисы.
 * Этот интерфейс наследует Serializable, что позволяет сериализовать объекты сообщений для передачи между системами.
 */
public interface MicroServiceMessage extends Serializable {

    /**
     * Получить общую информацию о сообщении.
     *
     * @return строка, содержащая общую информацию о сообщении.
     */
    String getCommonInfo();
}
