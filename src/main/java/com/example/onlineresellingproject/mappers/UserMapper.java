package com.example.onlineresellingproject.mappers;

import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper {

    public User mapToUser(UserEntity userEntity) {
        Integer id = Math.toIntExact(userEntity.getId());
        return new User(id,
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhone(),
                userEntity.getRole().name(),
                userEntity.getImage());
    }
}
