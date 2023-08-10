package com.example.onlineresellingproject.dto.user;

import com.example.onlineresellingproject.dto.DTO;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;

@Data
@MappedSuperclass
public abstract class UserDTO implements DTO {


}
