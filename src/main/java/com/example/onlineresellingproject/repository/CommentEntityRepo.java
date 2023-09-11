package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для доступа к данным комментариев (CommentEntity) в базе данных.
 */
@Repository
public interface CommentEntityRepo extends JpaRepository<CommentEntity, Long> {

    /**
     * Находит все комментарии, связанные с определенным объявлением.
     *
     * @param adId Идентификатор объявления, к которому привязаны комментарии.
     * @return Список комментариев, привязанных к указанному объявлению.
     */
    List<CommentEntity> findAllByAdId(Long adId);

}
