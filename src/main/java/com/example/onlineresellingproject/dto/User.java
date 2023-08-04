package com.example.onlineresellingproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Integer id;

    private String email;

    private String login;

    private String firstName;

    private String lastName;

    private String phone;

    private String role;

    private String image;
}