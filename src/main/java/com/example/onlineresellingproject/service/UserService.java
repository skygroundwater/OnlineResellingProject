package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.user.NewPassword;
import com.example.onlineresellingproject.dto.user.UpdateUser;
import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для работы с пользователями.
 */
public interface UserService extends UserDetailsService {

    /**
     * Создает новую сущность пользователя.
     *
     * @param model Сущность пользователя для создания.
     * @return Созданная сущность пользователя.
     */
    UserEntity post(UserEntity model);

    /**
     * Обновляет существующую сущность пользователя.
     *
     * @param model Сущность пользователя для обновления.
     * @return Обновленная сущность пользователя.
     */
    UserEntity patch(UserEntity model);

    /**
     * Создает или обновляет информацию о пользователе.
     *
     * @param userDetails Сведения о пользователе, полученные из Spring Security.
     * @param user        Обновленные сведения о пользователе.
     * @return Обновленные сведения о пользователе.
     */
    UpdateUser createOrUpdate(UserDetails userDetails, UpdateUser user);

    /**
     * Обновляет изображение пользователя.
     *
     * @param userDetails   Сведения о пользователе, полученные из Spring Security.
     * @param multipartFile Изображение пользователя в формате MultipartFile.
     * @return Обновленная сущность пользователя с новым изображением.
     */
    UserEntity updateImage(UserDetails userDetails, MultipartFile multipartFile);

    /**
     * Находит сущность пользователя по его логину.
     *
     * @param login Логин пользователя.
     * @return Сущность пользователя с указанным логином.
     */
    UserEntity findUserEntityByLogin(String login);

    /**
     * Проверяет, существует ли пользователь с указанным логином.
     *
     * @param login Логин пользователя.
     * @return true, если пользователь существует, в противном случае - false.
     */
    boolean userExists(String login);

    /**
     * Обновляет пароль пользователя.
     *
     * @param newPassword   Новый пароль пользователя.
     * @param userDetails   Сведения о пользователе, полученные из Spring Security.
     * @return Обновленная сущность пользователя с обновленным паролем.
     */
    UserEntity updateUserPassword(NewPassword newPassword, UserDetails userDetails);

    /**
     * Получает информацию о пользователе.
     *
     * @param userDetails Сведения о пользователе, полученные из Spring Security.
     * @return Информация о пользователе.
     */
    User getUser(UserDetails userDetails);
}
