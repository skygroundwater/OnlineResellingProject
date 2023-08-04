package com.example.onlineresellingproject.service;


import com.example.onlineresellingproject.dto.user.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
