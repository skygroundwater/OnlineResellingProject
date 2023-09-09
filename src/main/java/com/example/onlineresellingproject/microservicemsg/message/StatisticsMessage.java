package com.example.onlineresellingproject.microservicemsg.message;

import com.example.onlineresellingproject.OnlineResellingProjectApplication;
import com.example.onlineresellingproject.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * Класс StatisticsMessage представляет сообщение, используемое для сбора статистических данных о действиях пользователей.
 * Этот класс реализует интерфейс MicroServiceMessage и предоставляет информацию о действии пользователя и времени отправки.
 */
@Data
@Slf4j
@EqualsAndHashCode
public class StatisticsMessage implements MicroServiceMessage {

    /**
     * Счетчик для уникальных идентификаторов сообщений.
     */
    private static long counter;

    /**
     * Уникальный идентификатор сообщения.
     */
    private Long messageId;

    /**
     * Вложенная информация о пользователе.
     */
    private NestedUserInfo userInfo;

    /**
     * Время отправки сообщения.
     */
    private LocalDateTime timeSending;

    /**
     * Название службы отправителя.
     */
    private transient String fromServiceName;

    /**
     * Название службы получателя.
     */
    private transient String toServiceName;

    /**
     * Конструктор класса StatisticsMessage, который инициализирует объект сообщения на основе сущности UserEntity.
     *
     * @param userEntity Сущность пользователя для сбора информации.
     */
    public StatisticsMessage(UserEntity userEntity) {
        messageId = counter++;
        userInfo = new NestedUserInfo(userEntity);
        timeSending = LocalDateTime.now();
        toServiceName = "StatisticService";
        fromServiceName = OnlineResellingProjectApplication.class.getSimpleName();
    }

    /**
     * Возвращает общую информацию о сообщении, включая его тип, идентификатор, информацию о пользователе и службе отправителя и получателя.
     *
     * @return Строковое представление общей информации о сообщении.
     */
    @Override
    public String getCommonInfo() {
        return String.format("%s id %s, user %s from : %s to : %s",
                getClass().getSimpleName(), messageId,
                userInfo, fromServiceName, toServiceName);
    }

}
