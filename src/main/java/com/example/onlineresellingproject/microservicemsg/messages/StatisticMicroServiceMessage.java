package com.example.onlineresellingproject.microservicemsg.messages;

import com.example.onlineresellingproject.OnlineResellingProjectApplication;
import com.example.onlineresellingproject.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Slf4j
@EqualsAndHashCode
public class StatisticMicroServiceMessage implements MicroServiceMessage {

    private static long counter;

    private Long messageId;

    private NestedUserInfo userInfo;

    private LocalDateTime timeSending;

    private transient String fromServiceName;

    private transient String toServiceName;

    public StatisticMicroServiceMessage(UserEntity userEntity) {
        messageId = counter++;
        userInfo = new NestedUserInfo(userEntity);
        timeSending = LocalDateTime.now();
        toServiceName = "StatisticService";
        fromServiceName = OnlineResellingProjectApplication.class.getSimpleName();
    }

    @Override
    public String getCommonInfo() {
        return String.format("%s id %s, user %s from : %s to : %s",
                getClass().getSimpleName(), messageId,
                userInfo, fromServiceName, toServiceName);
    }

}
