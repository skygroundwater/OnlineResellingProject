package com.example.onlineresellingproject.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Класс, представляющий учетные данные для входа пользователя.
 * Этот класс наследует функциональность от абстрактного класса {@link Credentials}.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Login extends Credentials {

    /**
     * Имя пользователя (логин).
     */
    private String username;

    /**
     * Пароль пользователя в виде массива символов.
     */
    private char[] password;

    /**
     * Конструктор для создания объекта класса {@code Login} с заданным именем пользователя и паролем.
     *
     * @param username Имя пользователя (логин).
     * @param password Пароль пользователя в виде строки.
     */
    public Login(String username, String password) {
        this.username = username;
        this.password = password.toCharArray();
    }


}
