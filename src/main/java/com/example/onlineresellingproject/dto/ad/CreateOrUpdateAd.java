package com.example.onlineresellingproject.dto.ad;

import com.example.onlineresellingproject.entity.AdEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateOrUpdateAd extends AdDTO {

    String title;

    Integer price;

    String description;

    public static CreateOrUpdateAd map(AdEntity entity, ModelMapper modelMapper) {
        return modelMapper.map(entity, CreateOrUpdateAd.class);
    }
}
