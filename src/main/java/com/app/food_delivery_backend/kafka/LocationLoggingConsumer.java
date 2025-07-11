package com.app.food_delivery_backend.kafka;

import com.app.food_delivery_backend.model.LocationUpdate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LocationLoggingConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "location-updates", groupId = "logger-group")
    public void logLocation(String message) throws JsonProcessingException {
        System.out.println("📩 [LoggerConsumer] Raw message received: " + message);
        try {
            LocationUpdate update = objectMapper.readValue(message, LocationUpdate.class);
            System.out.println("🛰️ [LoggerConsumer] Agent '" + update.getAgentId() +
                    "' is at Latitude: " + update.getLatitude() +
                    ", Longitude: " + update.getLongitude() +
                    " at " + update.getTimestamp());
        } catch (Exception e) {
            System.err.println("❌ [LoggerConsumer] Failed to parse location: " + e.getMessage());
        }
    }
}
