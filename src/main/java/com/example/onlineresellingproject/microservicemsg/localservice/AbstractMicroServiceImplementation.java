package com.example.onlineresellingproject.microservicemsg.localservice;


import com.example.onlineresellingproject.microservicemsg.message.MicroServiceMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Абстрактный класс {@code AbstractMicroServiceImplementation} представляет базовую реализацию микросервиса для отправки
 * сообщений между компонентами системы через Apache Kafka.
 * Этот класс обеспечивает общую функциональность для отправки сообщений и является абстрактным, чтобы дать возможность
 * расширить его конкретными реализациями микросервисов.
 *
 * <p>Для создания конкретных реализаций микросервисов, необходимо унаследоваться от данного абстрактного класса
 * и реализовать абстрактный метод {@code onMessageReceived}, который будет обрабатывать входящие сообщения.
 *
 * @param <MSG> Тип сообщения, который будет отправлять/получать микросервис.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractMicroServiceImplementation<MSG extends MicroServiceMessage> implements MicroService<MSG> {

    /**
     * Шаблон Kafka, который используется для отправки сообщений.
     */
    private final KafkaTemplate<String, MSG> kafkaTemplate;

    /**
     * Название темы Kafka, на которую микросервис будет отправлять сообщения.
     */
    private String TOPIC_NAME;

    /**
     * Отправляет сообщение через Kafka на указанную тему.
     *
     * @param msg Сообщение для отправки.
     */
    @Override
    public final void send(MSG msg) {
        kafkaTemplate.send(TOPIC_NAME, msg);
    }

    /**
     * Абстрактный метод, который должен быть реализован в конкретной реализации микросервиса.
     * Метод будет вызываться при получении нового сообщения.
     *
     * @param message Полученное сообщение для обработки.
     */
    protected abstract void onMessageReceived(MSG message);

}
