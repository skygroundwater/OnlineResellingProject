package com.example.onlineresellingproject.config;

import com.example.onlineresellingproject.OnlineResellingProjectApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки логгера приложения.
 */
@Configuration
public class LoggerConfig {

    /**
     * Метод создает и настраивает бин для логгера приложения.
     *
     * @return Объект логгера, связанный с классом OnlineResellingProjectApplication.
     */
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(OnlineResellingProjectApplication.class);
    }

}
