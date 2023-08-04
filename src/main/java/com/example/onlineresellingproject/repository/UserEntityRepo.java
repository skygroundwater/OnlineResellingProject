package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepo extends JpaRepository<UserEntity, Long> {

}
