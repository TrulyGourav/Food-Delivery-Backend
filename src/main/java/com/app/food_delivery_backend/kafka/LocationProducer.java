package com.app.food_delivery_backend.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LocationProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "location-updates";

    public void send(String locationJson) {
        kafkaTemplate.send(TOPIC, locationJson);
    }
}
