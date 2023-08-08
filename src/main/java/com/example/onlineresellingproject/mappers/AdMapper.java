package com.example.onlineresellingproject.mappers;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdMapper implements Mapper {

    private final ModelMapper modelMapper;

    public AdMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Ad mapToAd(AdEntity entity) {
        return modelMapper.map(entity, Ad.class);
    }

    public ExtendedAd mapToExtendedAd(AdEntity entity) {
        return modelMapper.map(entity, ExtendedAd.class);
    }

    public Ads mapToAds(List<AdEntity> adEntities) {
        return Ads.builder()
                .ads(adEntities.stream().map(this::mapToAd)
                        .collect(Collectors.toList()))
                .count(adEntities.size()).build();
    }

    public AdEntity mapToEntity(Ad ad, UserEntity userEntity) {
        if (userEntity != null) {
            return AdEntity.builder()
                    .description(ad.getDescription())
                    .title(ad.getTitle()).price(ad.getPrice())
                    .createdAt(LocalDateTime.now())
                    .user(userEntity).image(ad.getImage())
                    .build();
        } else throw new NotFoundInDataBaseException("Пользователь не был найден");
    }
}
