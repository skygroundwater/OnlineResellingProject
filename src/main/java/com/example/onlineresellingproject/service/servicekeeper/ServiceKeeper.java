package com.example.onlineresellingproject.service.servicekeeper;

import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.service.AdService;
import com.example.onlineresellingproject.service.CommentService;
import com.example.onlineresellingproject.service.OnlineResellingProjectService;
import com.example.onlineresellingproject.service.UserService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public final class ServiceKeeper {

    private final OnlineResellingProjectService<UserEntity> userService;

    private final OnlineResellingProjectService<CommentEntity> commentService;

    private final OnlineResellingProjectService<AdEntity> adService;

    public ServiceKeeper(UserService userService,
                         CommentService commentService,
                         AdService adService) {
        this.userService = userService;
        this.commentService = commentService;
        this.adService = adService;
    }

}