package com.example.onlineresellingproject.mappers;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс {@code AdMapper} представляет собой компонент, отвечающий за преобразование сущностей и DTO
 * (Data Transfer Objects), связанных с объявлениями (Ad), в различные форматы и обратно.
 *
 * <p>Этот класс реализует интерфейс {@code Mapper}, который определяет методы для преобразования данных.
 * В частности, этот класс выполняет преобразование сущностей {@code AdEntity} в DTO {@code Ad} и {@code ExtendedAd},
 * а также преобразование списка сущностей в DTO {@code Ads}.
 *
 * @see Mapper
 * @see Ad
 * @see ExtendedAd
 * @see Ads
 * @see AdEntity
 */
@Component
public class AdMapper implements Mapper {

    /**
     * Метод {@code mapToAd} выполняет преобразование сущности объявления {@code AdEntity} в DTO объявления {@code Ad}.
     *
     * @param entity сущность объявления
     * @return объект DTO объявления
     */
    public Ad mapToAd(AdEntity entity) {
        Integer adId = Math.toIntExact(entity.getId());
        Integer userId = Math.toIntExact(entity.getUser().getId());
        return new Ad(adId,
                userId,
                entity.getImage(),
                entity.getPrice(),
                entity.getTitle());
    }

    /**
     * Метод {@code mapToExtendedAd} выполняет преобразование сущности объявления {@code AdEntity} в расширенное DTO
     * объявления {@code ExtendedAd}, которое содержит дополнительные данные об авторе объявления.
     *
     * @param entity сущность объявления
     * @return объект расширенного DTO объявления
     */
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

    /**
     * Метод {@code mapToAds} выполняет преобразование списка сущностей объявлений {@code AdEntity} в DTO объявлений {@code Ads},
     * включая количество объявлений и сами объявления.
     *
     * @param adEntities список сущностей объявлений
     * @return объект DTO объявлений
     */
    public Ads mapToAds(List<AdEntity> adEntities) {
        return Ads.builder()
                .results(adEntities.stream()
                        .map(this::mapToAd)
                        .collect(Collectors.toList()))
                .count(adEntities.size()).build();
    }
}
