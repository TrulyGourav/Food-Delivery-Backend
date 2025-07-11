package com.app.food_delivery_backend.controller;

import com.app.food_delivery_backend.model.LocationUpdate;
import com.app.food_delivery_backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
public class LocationQueryController {
    @Autowired
    private LocationService service;

    @GetMapping("/{agentId}")
    public ResponseEntity<LocationUpdate> getLatest(@PathVariable String agentId) {
        LocationUpdate location = service.getLatestLocation(agentId);
        System.out.println("location object: " + location);
        return ResponseEntity.ok(location);
    }
}
