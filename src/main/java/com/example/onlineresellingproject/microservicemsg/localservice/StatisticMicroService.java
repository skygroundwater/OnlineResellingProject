package com.example.onlineresellingproject.microservicemsg.localservice;

import com.example.onlineresellingproject.microservicemsg.message.StatisticsMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Абстрактный класс {@code StatisticMicroService} представляет собой базовую реализацию микросервиса для отправки
 * статистических сообщений через Apache Kafka.
 * <p>
 * Реализации этого класса предоставляют специфическую логику для отправки статистических сообщений в конкретные темы Kafka.
 * Для этого класс использует {@link KafkaTemplate} для отправки сообщений.
 */
@Service
public abstract class StatisticMicroService extends AbstractMicroServiceImplementation<StatisticsMessage> {

    /**
     * Создает экземпляр {@code StatisticMicroService} с заданным {@link KafkaTemplate} и именем темы Kafka.
     *
     * @param kafkaTemplate Кафка-шаблон для отправки сообщений.
     * @TOPIC_NAME - Имя темы Kafka, в которую будут отправляться статистические сообщения.
     */
    public StatisticMicroService(KafkaTemplate<String, StatisticsMessage> kafkaTemplate) {
        super(kafkaTemplate, "online_reselling_statistic");
    }

}