package com.example.onlineresellingproject.mappers;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdMapper implements Mapper {

    public Ad mapToAd(AdEntity entity) {
        Integer adId = Math.toIntExact(entity.getId());
        Integer userId = Math.toIntExact(entity.getUser().getId());
        return new Ad(adId,
                userId,
                entity.getImage(),
                entity.getPrice(),
                entity.getTitle());
    }

    public ExtendedAd mapToExtendedAd(AdEntity entity) {
        UserEntity user = entity.getUser();
        return ExtendedAd.builder()
                .email(user.getUsername())
                .authorFirstName(user.getFirstName())
                .authorLastName(user.getLastName())
                .phone(user.getPhone())
                .image(entity.getImage())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .title(entity.getTitle())
                .pk(Math.toIntExact(entity.getId()))
                .build();
    }

    public Ads mapToAds(List<AdEntity> adEntities) {
        return Ads.builder()
                .results(adEntities.stream()
                        .map(this::mapToAd)
                        .collect(Collectors.toList()))
                .count(adEntities.size()).build();
    }
}
