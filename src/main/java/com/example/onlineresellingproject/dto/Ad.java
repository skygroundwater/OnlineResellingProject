package com.example.onlineresellingproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ad {

    private Integer author;

    private String image;

    private Integer ad;

    private Integer price;

    private String title;

}
