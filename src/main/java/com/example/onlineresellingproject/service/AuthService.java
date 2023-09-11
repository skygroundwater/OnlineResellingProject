package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.user.Register;

/**
 * Сервис для аутентификации и регистрации пользователей.
 */
public interface AuthService {

    /**
     * Проверяет аутентификацию пользователя по имени пользователя и паролю.
     *
     * @param userName Имя пользователя.
     * @param password Пароль пользователя.
     * @return true, если пользователь успешно аутентифицирован, в противном случае false.
     */
    boolean login(String userName, char[] password);

    /**
     * Регистрирует нового пользователя на основе предоставленных данных.
     *
     * @param register Модель данных пользователя для регистрации.
     * @return true, если регистрация прошла успешно, в противном случае false.
     */
    boolean register(Register register);
}
