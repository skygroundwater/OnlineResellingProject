package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface AdEntityRepo extends JpaRepository<AdEntity, Long> {

    Optional<AdEntity> findAdEntityByTitle(String title);

    List<AdEntity> findAdEntitiesByUser(UserEntity userEntity);

}
