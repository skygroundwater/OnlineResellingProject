package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.repository.UserEntityRepo;
import com.example.onlineresellingproject.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<UserEntity, Long, UserEntityRepo> implements UserService {

    public UserServiceImpl(UserEntityRepo userEntityRepo) {
        super(userEntityRepo);
    }
}
