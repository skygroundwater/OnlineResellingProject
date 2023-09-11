package com.example.onlineresellingproject.microservicemsg.localservice;

import com.example.onlineresellingproject.microservicemsg.message.MicroServiceMessage;

/**
 * Интерфейс {@code MicroService} представляет общий контракт для микросервисов, предназначенных для отправки сообщений
 * между компонентами системы через Apache Kafka.
 *
 * @param <MSG> Тип сообщения, который будет отправлять/получать микросервис.
 */
public interface MicroService<MSG extends MicroServiceMessage> {

    /**
     * Отправляет сообщение через микросервис на другие компоненты системы.
     *
     * @param msg Сообщение для отправки.
     */
    void send(MSG msg);
}
