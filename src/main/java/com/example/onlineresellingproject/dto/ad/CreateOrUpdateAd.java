package com.example.onlineresellingproject.dto.ad;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateOrUpdateAd extends AdDTO{

    String title;

    Integer price;

    String description;
}
