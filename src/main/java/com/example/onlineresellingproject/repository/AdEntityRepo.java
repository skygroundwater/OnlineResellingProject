package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdEntityRepo extends JpaRepository<AdEntity, Long> {
}
