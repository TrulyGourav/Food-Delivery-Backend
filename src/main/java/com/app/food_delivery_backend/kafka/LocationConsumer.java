package com.app.food_delivery_backend.kafka;

import com.app.food_delivery_backend.model.LocationUpdate;
import com.app.food_delivery_backend.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LocationConsumer {
    @Autowired
    private LocationService service;

    @Autowired
    private ObjectMapper objectMapper;

//    @KafkaListener(topics = "location-updates", groupId = "tracker-group")
//    public void consume(String message) throws JsonProcessingException {
//        System.out.println("Received Kafka message: " + message);
//        ObjectMapper mapper = new ObjectMapper();
//        LocationUpdate update = mapper.readValue(message, LocationUpdate.class);
//        service.saveLocation(update);
//    }

    @KafkaListener(topics = "location-updates", groupId = "tracker-group")
    public void consume(String message) {
        System.out.println("📩 [DB Consumer] Raw message received: " + message);
        try {
            LocationUpdate update = objectMapper.readValue(message, LocationUpdate.class);
            System.out.println("✅ [DB Consumer] Deserialized: " + update);
            service.saveLocation(update);
        } catch (Exception e) {
            System.err.println("❌ Error in [DB Consumer]: " + e.getMessage());
//            e.printStackTrace();
        }
    }
}
