package com.example.onlineresellingproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class OnlineResellingProjectApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(OnlineResellingProjectApplication.class);
        logger.debug("*** Start Online Reselling Project Application ***");
        SpringApplication.run(OnlineResellingProjectApplication.class, args);
    }

}
