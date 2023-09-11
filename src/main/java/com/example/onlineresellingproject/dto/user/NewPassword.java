package com.example.onlineresellingproject.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Класс, представляющий новый пароль пользователя.
 * Этот класс наследует функциональность от абстрактного класса {@link Credentials}.
 */
@Data
@Builder
@EqualsAndHashCode
public class NewPassword {

    /**
     * Текущий пароль пользователя.
     */
    private String currentPassword;

    /**
     * Новый пароль пользователя.
     */
    private String newPassword;

}
