package com.example.onlineresellingproject.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Login {

    private String username;
    private char[] password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password.toCharArray();
    }


}
