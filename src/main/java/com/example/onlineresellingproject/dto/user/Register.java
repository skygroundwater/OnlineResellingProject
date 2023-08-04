package com.example.onlineresellingproject.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Register extends UserDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
