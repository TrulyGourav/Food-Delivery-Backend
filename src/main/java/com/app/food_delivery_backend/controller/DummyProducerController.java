package com.app.food_delivery_backend.controller;

import com.app.food_delivery_backend.kafka.LocationProducer;
import com.app.food_delivery_backend.model.LocationUpdate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyProducerController {
    @Autowired
    private LocationProducer producer;

    @PostMapping("/api/send-location")
    public ResponseEntity<String> sendLocation(@RequestBody LocationUpdate update) throws JsonProcessingException {
        if (update == null) {
            return ResponseEntity.badRequest().body("Invalid payload: LocationUpdate is null");
        }
        System.out.println("📥 Received update: " + update);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // just to be safe in this controller
        String json = mapper.writeValueAsString(update);

        System.out.println("🚀 Sending to Kafka: " + json);
        producer.send(json);

        return ResponseEntity.ok("✅ Location published to Kafka");
    }
}
