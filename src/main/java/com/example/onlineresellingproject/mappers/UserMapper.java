package com.example.onlineresellingproject.mappers;

import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Класс {@code UserMapper} представляет собой маппер для преобразования данных между сущностями пользователей
 * ({@code UserEntity}) и объектами передачи данных о пользователях ({@code User}) в приложении.
 * Этот класс выполняет конвертацию данных о пользователе из формата сущности в формат DTO и обратно.
 *
 * <p>Маппер предоставляет методы для выполнения преобразований, что позволяет отделить бизнес-логику от слоя данных.
 *
 * @see com.example.onlineresellingproject.dto.user.User
 * @see com.example.onlineresellingproject.entity.UserEntity
 */
@Component
public class UserMapper implements Mapper {

    /**
     * Преобразует сущность пользователя ({@code UserEntity}) в объект передачи данных о пользователе ({@code User}).
     *
     * @param userEntity Сущность пользователя для преобразования.
     * @return Объект передачи данных о пользователе ({@code User}).
     */
    public User mapToUser(UserEntity userEntity) {
        Integer id = Math.toIntExact(userEntity.getId());
        return new User(id,
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhone(),
                userEntity.getRole().name(),
                userEntity.getImage());
    }
}
