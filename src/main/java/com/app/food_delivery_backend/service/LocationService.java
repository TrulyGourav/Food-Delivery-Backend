package com.app.food_delivery_backend.service;

import com.app.food_delivery_backend.model.LocationUpdate;
import com.app.food_delivery_backend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    public void saveLocation(LocationUpdate update) {
        repository.save(update);
    }

    public LocationUpdate getLatestLocation(String agentId) {
        return repository.findTop1ByAgentIdOrderByTimestampDesc(agentId).stream().findFirst().orElse(null);
    }
}
