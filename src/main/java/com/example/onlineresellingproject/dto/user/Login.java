package com.example.onlineresellingproject.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Login extends Credentials {

    private String username;
    private char[] password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password.toCharArray();
    }


}
