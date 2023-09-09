package com.example.onlineresellingproject.config;

import com.example.onlineresellingproject.microservicemsg.message.StatisticsMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурационный класс для настройки связи с Kafka и отправки сообщений статистики.
 */
@Configuration
public class StatisticsServiceConfig {

    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    /**
     * Метод создает и настраивает фабрику для создания Kafka-продюсера.
     *
     * @return Фабрика для создания Kafka-продюсера с настроенными параметрами.
     */
    @Bean
    public ProducerFactory<String, StatisticsMessage> producerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    /**
     * Метод создает Kafka-шаблон для отправки сообщений статистики.
     *
     * @return Kafka-шаблон для отправки сообщений статистики с использованием фабрики продюсера.
     */
    @Bean
    public KafkaTemplate<String, StatisticsMessage> statisticServiceMessageKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
