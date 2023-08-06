package com.example.onlineresellingproject.dto.ad;

import com.example.onlineresellingproject.entity.AdEntity;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Ads {

    private Integer count;

    private List<Ad> ads;

    public static Ads map(List<AdEntity> adEntities, ModelMapper modelMapper) {
        return Ads.builder()
                .ads(adEntities.stream().map(adEntity -> Ad.mapToDTo(adEntity, modelMapper))
                        .collect(Collectors.toList()))
                .count(adEntities.size()).build();
    }
}
