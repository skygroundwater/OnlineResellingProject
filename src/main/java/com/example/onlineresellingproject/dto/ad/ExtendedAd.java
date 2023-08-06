package com.example.onlineresellingproject.dto.ad;

import com.example.onlineresellingproject.entity.AdEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ExtendedAd extends AdDTO {

    private Long id;

    private String userFirstName;

    private String userLastName;

    private String description;

    private String userEmail;

    private String image;

    private String userPhone;

    private Integer price;

    private String title;

    public static ExtendedAd map(AdEntity entity, ModelMapper modelMapper){
        return modelMapper.map(entity, ExtendedAd.class);
    }
}
