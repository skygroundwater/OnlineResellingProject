package com.example.onlineresellingproject.dto.ad;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Ad extends AdDTO {

    private Long author;

    private String image;

    private Long ad;

    private Integer price;

    private String title;

}
