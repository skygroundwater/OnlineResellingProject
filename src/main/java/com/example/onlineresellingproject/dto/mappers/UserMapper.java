package com.example.onlineresellingproject.dto.mappers;

import com.example.onlineresellingproject.dto.user.UserDTO;
import com.example.onlineresellingproject.entity.UserEntity;

public interface UserMapper<U extends UserDTO> extends Mapper<UserEntity, U> {
}
