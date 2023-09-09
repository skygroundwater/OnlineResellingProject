package com.example.onlineresellingproject.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для сохранения изображений пользователей и объявлений.
 */
public interface FilesService {

    /**
     * Сохраняет изображение пользователя.
     *
     * @param file Изображение в формате MultipartFile.
     * @return Путь к сохраненному изображению пользователя.
     */
    String saveUserImage(MultipartFile file);

    /**
     * Сохраняет изображение объявления.
     *
     * @param file Изображение в формате MultipartFile.
     * @return Путь к сохраненному изображению объявления.
     */
    String saveAdsImage(MultipartFile file);

}
