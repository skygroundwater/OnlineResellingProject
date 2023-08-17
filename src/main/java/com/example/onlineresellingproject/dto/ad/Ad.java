package com.example.onlineresellingproject.dto.ad;

import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad extends AdDTO {

    private Integer pk;

    private Integer author;

    private String image;

    private Integer price;

    private String title;

}