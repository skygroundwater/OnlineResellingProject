package com.example.onlineresellingproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrUpdateAd {

    String title;

    Integer price;

    String description;
}
