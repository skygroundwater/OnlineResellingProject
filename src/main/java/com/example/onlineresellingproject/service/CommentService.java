package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.entity.UserEntity;

/**
 * Сервис для работы с комментариями.
 */
public interface CommentService {

    /**
     * Создает новый комментарий на основе предоставленной модели.
     *
     * @param model Модель данных комментария для создания.
     * @return Созданный комментарий.
     */
    CommentEntity post(CommentEntity model);

    /**
     * Обновляет существующий комментарий на основе предоставленной модели.
     *
     * @param model Модель данных комментария для обновления.
     * @return Обновленный комментарий.
     */
    CommentEntity patch(CommentEntity model);

    /**
     * Удаляет комментарий по указанному идентификатору.
     *
     * @param id Идентификатор комментария для удаления.
     */
    void delete(Long id);

    /**
     * Получает комментарий по указанному идентификатору.
     *
     * @param id Идентификатор комментария для получения.
     * @return Комментарий.
     */
    CommentEntity get(Long id);

    /**
     * Создает новый комментарий пользователя для объявления.
     *
     * @param user       Пользователь, создающий комментарий.
     * @param adEntity   Объявление, к которому относится комментарий.
     * @param dto        Модель данных комментария для создания.
     * @return Созданный комментарий.
     */
    Comment create(UserEntity user, AdEntity adEntity, CreateOrUpdateComment dto);

    /**
     * Обновляет существующий комментарий по указанному идентификатору.
     *
     * @param id  Идентификатор комментария для обновления.
     * @param dto Модель данных комментария для обновления.
     * @return Обновленный комментарий.
     */
    Comment update(Long id, CreateOrUpdateComment dto);

    /**
     * Получает все комментарии для указанного объявления по его идентификатору.
     *
     * @param id Идентификатор объявления.
     * @return Комментарии для указанного объявления.
     */
    Comments findCommentsByAdId(Long id);

}
