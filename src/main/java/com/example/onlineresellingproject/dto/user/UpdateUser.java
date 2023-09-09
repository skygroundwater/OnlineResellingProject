package com.example.onlineresellingproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UpdateUser extends UserDTO {

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

}
