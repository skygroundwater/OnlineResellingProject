package com.example.onlineresellingproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtendedAd {

    private Integer pk;

    private String authorFirstName;

    private String authorLastName;

    private String description;

    private String email;

    private String image;

    private String phone;

    private Integer price;

    private String title;
}
