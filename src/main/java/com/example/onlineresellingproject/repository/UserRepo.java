package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepo extends JpaRepository<User, Long> {
}
