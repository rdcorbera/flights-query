package com.skyxairlines.services.flightquery.infrastructure.repositories;

import com.skyxairlines.services.flightquery.infrastructure.entities.Leg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegRepository extends JpaRepository<Leg, Integer> {
}
