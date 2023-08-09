package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends OnlineResellingProjectService<UserEntity, Long, User>, UpdateImageService<UserEntity>, UserDetailsService {

    UserEntity findUserEntityByLogin(String login);

    boolean userExists(String Login);

}
