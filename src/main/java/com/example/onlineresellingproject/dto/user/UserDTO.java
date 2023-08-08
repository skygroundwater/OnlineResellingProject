package com.example.onlineresellingproject.dto.user;

import com.example.onlineresellingproject.dto.DTO;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public abstract class UserDTO implements DTO {

    private String firstName;

    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

}
