package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для управления объявлениями и связанными операциями.
 */
public interface AdService {

    /**
     * Создает новую сущность объявления.
     *
     * @param model Модель объявления для создания.
     * @return Созданная сущность объявления.
     */
    AdEntity post(AdEntity model);

    /**
     * Обновляет сущность объявления.
     *
     * @param model Модель объявления для обновления.
     * @return Обновленная сущность объявления.
     */
    AdEntity patch(AdEntity model);

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id Идентификатор объявления для удаления.
     */
    void delete(Long id);

    /**
     * Получает сущность объявления по его идентификатору.
     *
     * @param id Идентификатор объявления.
     * @return Сущность объявления.
     */
    AdEntity get(Long id);

    /**
     * Получает список всех объявлений.
     *
     * @return Объект, содержащий список объявлений.
     */
    Ads getAds();

    /**
     * Создает новое объявление, связывая его с пользователем и сохраняя изображение объявления.
     *
     * @param userEntity     Пользователь, создающий объявление.
     * @param dto            Модель данных для создания объявления.
     * @param multipartFile  Мультимедийный файл (изображение) объявления.
     * @return Созданное объявление.
     */
    Ad create(UserEntity userEntity,
              CreateOrUpdateAd dto,
              MultipartFile multipartFile);

    /**
     * Обновляет изображение объявления.
     *
     * @param id             Идентификатор объявления для обновления изображения.
     * @param userEntity     Пользователь, обновляющий изображение объявления.
     * @param multipartFile  Мультимедийный файл (изображение) объявления.
     * @return Обновленное объявление.
     */
    Ad updateImage(Long id, UserEntity userEntity, MultipartFile multipartFile);

    /**
     * Находит все объявления, связанные с конкретным пользователем.
     *
     * @param userEntity Пользователь, для которого выполняется поиск объявлений.
     * @return Объект, содержащий список объявлений пользователя.
     */
    Ads findUserAds(UserEntity userEntity);

    /**
     * Удаляет объявление пользователя по его идентификатору.
     *
     * @param userEntity Пользователь, удаляющий объявление.
     * @param id         Идентификатор объявления для удаления.
     * @return Сообщение об успешном удалении объявления.
     */
    String deleteAd(UserEntity userEntity, Long id);

    /**
     * Получает расширенную информацию об объявлении по его идентификатору.
     *
     * @param id Идентификатор объявления.
     * @return Расширенная информация об объявлении.
     */
    ExtendedAd getExtendedAd(Long id);

    /**
     * Обновляет данные объявления по его идентификатору.
     *
     * @param id                 Идентификатор объявления для обновления.
     * @param createOrUpdateAd   Модель данных для обновления объявления.
     * @return Обновленное объявление.
     */
    Ad updateAd(Long id, CreateOrUpdateAd createOrUpdateAd);
}
