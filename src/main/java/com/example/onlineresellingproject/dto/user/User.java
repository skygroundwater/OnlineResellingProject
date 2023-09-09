package com.example.onlineresellingproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * Класс, представляющий пользователя системы онлайн-продаж.
 * Расширяет абстрактный класс {@link UserDTO}.
 */
public class User extends UserDTO {

    /**
     * Уникальный идентификатор пользователя.
     */
    private Integer id;

    /**
     * Адрес электронной почты пользователя.
     */
    private String email;

    /**
     * Имя пользователя.
     */
    private String firstName;

    /**
     * Фамилия пользователя.
     */
    private String lastName;

    /**
     * Номер телефона пользователя в формате +7 (XXX) XXX-XX-XX.
     */
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    /**
     * Роль пользователя (USER или ADMIN).
     */
    private String role;

    /**
     * Путь к изображению пользователя.
     */
    private String image;
}