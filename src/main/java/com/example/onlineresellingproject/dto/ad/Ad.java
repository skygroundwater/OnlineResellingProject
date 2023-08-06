package com.example.onlineresellingproject.dto.ad;

import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.service.OnlineResellingProjectService;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Ad extends AdDTO {

    private Long id;

    private Long userId;

    private String image;

    private Integer price;

    private String title;

    private String description;

    public static Ad mapToDTo(AdEntity entity, ModelMapper modelMapper) {
        if (entity != null) {
            return modelMapper.map(entity, Ad.class);
        } else throw new NotFoundInDataBaseException("Объявление не было найдено");
    }

    public static AdEntity mapToEntity(Ad ad, OnlineResellingProjectService<UserEntity, Long, User> userService) {
        UserEntity userEntity = userService.get(ad.userId);
        if (userEntity != null) {
            return AdEntity.builder()
                    .description(ad.description)
                    .title(ad.title).price(ad.price)
                    .createdAt(LocalDateTime.now())
                    .user(userEntity).image(ad.image)
                    .build();
        } else throw new NotFoundInDataBaseException("Пользователь не был найден");
    }
}