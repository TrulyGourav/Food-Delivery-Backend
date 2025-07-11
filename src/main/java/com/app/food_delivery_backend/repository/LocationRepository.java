package com.app.food_delivery_backend.repository;

import com.app.food_delivery_backend.model.LocationUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationUpdate, Long> {
    List<LocationUpdate> findTop1ByAgentIdOrderByTimestampDesc(String agentId);
}
