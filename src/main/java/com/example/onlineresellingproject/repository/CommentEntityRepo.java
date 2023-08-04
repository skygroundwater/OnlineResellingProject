package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentEntityRepo extends JpaRepository<CommentEntity, Long> {
}
