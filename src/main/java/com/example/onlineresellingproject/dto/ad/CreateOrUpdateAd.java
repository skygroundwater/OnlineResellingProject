package com.example.onlineresellingproject.dto.ad;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateOrUpdateAd extends AdDTO {

    private String title;

    private Integer price;

    private String description;

}
