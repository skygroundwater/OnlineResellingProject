package com.example.onlineresellingproject.config;

import com.example.onlineresellingproject.OnlineResellingProjectApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    /**
     * Инициализируем логгера
     * @return
     */
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(OnlineResellingProjectApplication.class);
    }

}
