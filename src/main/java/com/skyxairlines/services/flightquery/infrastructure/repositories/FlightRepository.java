package com.skyxairlines.services.flightquery.infrastructure.repositories;

import com.skyxairlines.services.flightquery.infrastructure.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {
}
