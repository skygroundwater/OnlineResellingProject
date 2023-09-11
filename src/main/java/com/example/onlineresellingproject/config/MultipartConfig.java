package com.example.onlineresellingproject.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * Конфигурационный класс для настройки параметров мультипарт-загрузки файлов.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MultipartConfig {

    /**
     * Метод создает и настраивает бин для параметров мультипарт-загрузки файлов.
     *
     * @return Объект MultipartConfigElement с настроенными параметрами максимального размера файла и запроса.
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("5000KB"));
        factory.setMaxRequestSize(DataSize.parse("5000KB"));
        return factory.createMultipartConfig();
    }
}
