package com.example.onlineresellingproject.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Класс, представляющий данные для регистрации нового пользователя.
 * Этот класс наследует функциональность от абстрактного класса {@link Credentials}.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Register extends Credentials {

    /**
     * Имя пользователя для регистрации.
     */
    private String username;

    /**
     * Пароль пользователя для регистрации.
     */
    private char[] password;

    /**
     * Имя пользователя.
     */
    private String firstName;
    /**
     * Фамилия пользователя.
     */
    private String lastName;

    /**
     * Телефон пользователя.
     */
    private String phone;

    /**
     * Роль пользователя.
     */
    private Role role;

    /**
     * Конструктор для создания объекта класса {@code Register}.
     *
     * @param username  Имя пользователя для регистрации.
     * @param password  Пароль пользователя для регистрации.
     * @param firstName Имя пользователя.
     * @param lastName  Фамилия пользователя.
     * @param phone     Телефон пользователя.
     * @param role      Роль пользователя.
     */
    public Register(String username,
                    String password,
                    String firstName,
                    String lastName,
                    String phone,
                    Role role) {
        this.username = username;
        this.password = password.toCharArray();
        this.firstName = firstName;
        this.role = role;
        this.lastName = lastName;
        this.phone = phone;
    }
}
