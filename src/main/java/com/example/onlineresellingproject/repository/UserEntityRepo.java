package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для доступа к данным пользователей (UserEntity) в базе данных.
 */
@Repository
public interface UserEntityRepo extends JpaRepository<UserEntity, Long> {

    /**
     * Находит пользователя по его имени пользователя (username).
     *
     * @param username Имя пользователя, по которому производится поиск.
     * @return Optional, содержащий найденного пользователя или пустой, если пользователь не найден.
     */
    Optional<UserEntity> findUserEntityByUsername(String username);

    /**
     * Проверяет существование пользователя с указанным именем пользователя (username).
     *
     * @param username Имя пользователя, наличие которого проверяется.
     * @return true, если пользователь с указанным именем пользователя существует, в противном случае - false.
     */
    boolean existsUserEntityByUsername(String username);

}
