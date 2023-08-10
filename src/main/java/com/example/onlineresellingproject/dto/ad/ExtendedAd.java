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

    private Integer pk;

    private String authorFirstName;

    private String authorLastName;

    private String description;

    private String email;

    private String image;

    private String phone;

    private Integer price;

    private String title;

}
