package com.example.onlineresellingproject.microservicemsg.message;

import com.example.onlineresellingproject.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Класс NestedUserInfo представляет информацию о пользователе, необходимую для встроенной (nested) структуры сообщений.
 * Этот класс реализует Serializable, что позволяет сериализовать объекты для передачи данных между системами.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
final class NestedUserInfo implements Serializable {

    /**
     * Уникальный идентификатор пользователя.
     */
    private Long id;

    /**
     * Имя пользователя.
     */
    private String username;

    /**
     * Имя пользователя (first name).
     */
    private String firstName;

    /**
     * Фамилия пользователя (last name).
     */
    private String lastName;

    NestedUserInfo(UserEntity userEntity) {
        id = userEntity.getId();
        username = userEntity.getUsername();
        firstName = userEntity.getFirstName();
        lastName = userEntity.getLastName();
    }

    /**
     * Возвращает строковое представление объекта NestedUserInfo.
     *
     * @return Строковое представление объекта с информацией о пользователе.
     */
    public String toString() {
        return String.format("User id %s username : %s; firstname : %s; lastname : %s ",
                id, username, firstName, lastName);
    }
}
