package com.example.onlineresellingproject.dto.ad;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad extends AdDTO {

    private Long id;

    private Long userId;

    private String image;

    private Integer price;

    private String title;

    private String description;

}