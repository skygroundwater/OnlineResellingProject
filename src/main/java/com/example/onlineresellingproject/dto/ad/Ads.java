package com.example.onlineresellingproject.dto.ad;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Ads {

    private Integer count;

    private List<Ad> ads;
}
