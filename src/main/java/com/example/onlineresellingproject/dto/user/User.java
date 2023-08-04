package com.example.onlineresellingproject.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class User extends UserDTO {

    private Long id;

    private String email;

    private String login;

    private String firstName;

    private String lastName;

    private String phone;

    private String role;

    private String image;
}