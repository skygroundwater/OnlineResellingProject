package com.example.onlineresellingproject.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UpdateUser extends UserDTO {

    private String firstName;

    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

}
