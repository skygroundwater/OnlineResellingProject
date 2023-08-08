package com.example.onlineresellingproject.service;


import com.example.onlineresellingproject.dto.Register;

public interface  AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
