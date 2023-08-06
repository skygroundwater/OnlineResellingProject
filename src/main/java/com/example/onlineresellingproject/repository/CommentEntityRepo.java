package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentEntityRepo extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByAd_Id(Long adId);

}
