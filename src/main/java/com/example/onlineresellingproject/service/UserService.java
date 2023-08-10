package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.user.NewPassword;
import com.example.onlineresellingproject.dto.user.UpdateUser;
import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntity post(UserEntity model);

    UserEntity patch(UserEntity model);

    UpdateUser createOrUpdate(UserDetails userDetails, UpdateUser user);

    UserEntity findUserEntityByLogin(String login);

    boolean userExists(String Login);

    UserEntity updateUserPassword(NewPassword newPassword, UserDetails userDetails);

}
