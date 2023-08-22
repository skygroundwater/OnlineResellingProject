package com.example.onlineresellingproject.microservicemsg.localservices;

import com.example.onlineresellingproject.microservicemsg.messages.StatisticMicroServiceMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StatisticMicroService extends AbstractMicroServiceImplementation<StatisticMicroServiceMessage> {

    public StatisticMicroService(KafkaTemplate<String, StatisticMicroServiceMessage> kafkaTemplate) {
        super(kafkaTemplate);
        setTOPIC_NAME("online_reselling_statistic");
    }

}