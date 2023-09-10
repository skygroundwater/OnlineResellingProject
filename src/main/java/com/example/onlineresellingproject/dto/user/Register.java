package com.example.onlineresellingproject.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class Register {

    private String username;
    private char[] password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public Register(String username,
                    String password,
                    String firstName,
                    String lastName,
                    String phone,
                    Role role) {
        this.username = username;
        this.password = password.toCharArray();
        this.firstName = firstName;
        this.role = role;
        this.lastName = lastName;
        this.phone = phone;
    }
}
