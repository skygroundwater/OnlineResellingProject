package com.example.onlineresellingproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Конфигурационный класс для настройки шифрования паролей в системе безопасности.
 */
@Configuration
public class EncoderSecurityConfig {

    /**
     * Метод создает и настраивает бин для шифрования паролей с использованием BCryptPasswordEncoder.
     *
     * @return Объект PasswordEncoder для шифрования паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
