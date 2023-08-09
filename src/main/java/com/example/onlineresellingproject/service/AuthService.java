package com.example.onlineresellingproject.service;


import com.example.onlineresellingproject.dto.user.Register;

public interface AuthService {
    boolean login(String userName, char[] password);

    boolean register(Register register);
}
