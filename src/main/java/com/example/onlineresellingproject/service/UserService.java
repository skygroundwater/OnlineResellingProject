package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.UserEntity;

public interface UserService extends OnlineResellingProjectService<UserEntity, Long, User>, UpdateImageService<UserEntity> {

}
