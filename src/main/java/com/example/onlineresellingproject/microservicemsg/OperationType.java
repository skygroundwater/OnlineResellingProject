package com.example.onlineresellingproject.microservicemsg;

/**
 * Перечисление OperationType представляет типы операций, которые могут выполняться в системе.
 * Каждая операция связана с изменением данных и используется для указания типа действия, выполняемого над объектами.
 */
public enum OperationType {

    /**
     * Обновление (изменение) существующего объекта.
     */
    UPDATE,

    /**
     * Создание нового объекта.
     */
    CREATE,

    /**
     * Удаление объекта.
     */
    DELETE,

    /**
     * Обновление (изменение) изображения объекта.
     */
    UPDATE_IMAGE
}
