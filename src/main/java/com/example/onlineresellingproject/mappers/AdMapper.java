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
                .ads(adEntities.stream()
                        .map(this::mapToAd)
                        .collect(Collectors.toList()))
                .count(adEntities.size()).build();
    }

    public AdEntity mapToEntity(Ad ad, UserEntity userEntity) {
        if (userEntity != null) {
            return AdEntity.builder()
                    .title(ad.getTitle()).price(ad.getPrice())
                    .createdAt(LocalDateTime.now())
                    .user(userEntity).image(ad.getImage())
                    .build();
        } else throw new NotFoundInDataBaseException("Пользователь не был найден");
    }
}
