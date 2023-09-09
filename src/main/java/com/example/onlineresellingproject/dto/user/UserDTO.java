package com.example.onlineresellingproject.dto.user;

import com.example.onlineresellingproject.dto.DTO;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass

/**
 * Абстрактный класс, представляющий общий интерфейс для всех классов,
 * представляющих информацию о пользователе (DTO).
 * Этот класс является абстрактным и содержит общие поля для пользовательских DTO.
 */
public abstract class UserDTO implements DTO {


}
