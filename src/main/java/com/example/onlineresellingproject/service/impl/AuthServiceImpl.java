package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.user.Register;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.service.AuthService;
import com.example.onlineresellingproject.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class  AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserService userService,
                           PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, char[] password) {
        if (!userService.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = userService.loadUserByUsername(userName);
        return encoder.matches(CharBuffer.wrap(password), userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (userService.userExists(register.getUsername())) {
            return false;
        }
        userService.post(
                UserEntity.builder()
                        .password(encoder.encode(CharBuffer.wrap(register.getPassword())))
                        .username(register.getUsername())
                        .firstName(register.getFirstName())
                        .lastName(register.getLastName())
                        .phone(register.getPhone())
                        .role(register.getRole())
                        .build());
        return true;
    }

}
