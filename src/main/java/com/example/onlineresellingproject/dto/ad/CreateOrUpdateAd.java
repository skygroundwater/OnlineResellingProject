package com.example.onlineresellingproject.dto.ad;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrUpdateAd {

    private String title;

    private Integer price;

    private String description;

}
