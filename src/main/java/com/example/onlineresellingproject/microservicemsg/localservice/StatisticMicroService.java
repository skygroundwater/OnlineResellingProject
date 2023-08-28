package com.example.onlineresellingproject.microservicemsg.localservice;

import com.example.onlineresellingproject.microservicemsg.message.StatisticsMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StatisticMicroService extends AbstractMicroServiceImplementation<StatisticsMessage> {

    public StatisticMicroService(KafkaTemplate<String, StatisticsMessage> kafkaTemplate) {
        super(kafkaTemplate, "online_reselling_statistic");
    }

}