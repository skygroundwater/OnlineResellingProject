package com.example.onlineresellingproject.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Login extends UserDTO {
    private String username;
    private String password;
}
