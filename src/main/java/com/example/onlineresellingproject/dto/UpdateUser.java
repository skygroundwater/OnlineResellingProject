package com.example.onlineresellingproject.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
@Data
@Builder
public class UpdateUser {

    private String firstName;

    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

}
