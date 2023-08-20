package com.example.onlineresellingproject.microservicemsg.localservices;


import com.example.onlineresellingproject.microservicemsg.messages.MicroServiceMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractMicroServiceImplementation<MSG extends MicroServiceMessage> implements MicroServiceInterface<MSG>{

    private final KafkaTemplate<String, MSG> kafkaTemplate;

    private String TOPIC_NAME;

    @Override
    public final void send(MSG msg) {
        kafkaTemplate.send(TOPIC_NAME, msg);
    }

}
