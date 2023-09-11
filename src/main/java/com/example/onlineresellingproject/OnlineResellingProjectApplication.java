package com.example.onlineresellingproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Главный класс приложения Online Reselling Project.
 */
@SpringBootApplication
@EnableKafka
public class OnlineResellingProjectApplication {

    /**
     * Главный метод, запускающий приложение.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        // Инициализация логгера
        Logger logger = LoggerFactory.getLogger(OnlineResellingProjectApplication.class);
        // Вывод сообщения о запуске приложения в режиме отладки
        logger.debug("*** Start Online Reselling Project Application ***");
        // Запуск Spring Boot-приложения
        SpringApplication.run(OnlineResellingProjectApplication.class, args);
    }

}
