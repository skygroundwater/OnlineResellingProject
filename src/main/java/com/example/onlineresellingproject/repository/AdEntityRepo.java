package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для доступа к данным объявлений (AdEntity) в базе данных.
 */
@Repository
public interface AdEntityRepo extends JpaRepository<AdEntity, Long> {

    /**
     * Находит объявление по его заголовку.
     *
     * @param title Заголовок объявления для поиска.
     * @return Объект Optional с найденным объявлением (если существует).
     */
    Optional<AdEntity> findAdEntityByTitle(String title);

    /**
     * Находит список объявлений, связанных с определенным пользователем.
     *
     * @param userEntity Пользователь, к которому привязаны объявления.
     * @return Список объявлений, принадлежащих указанному пользователю.
     */
    List<AdEntity> findAdEntitiesByUser(UserEntity userEntity);

}
