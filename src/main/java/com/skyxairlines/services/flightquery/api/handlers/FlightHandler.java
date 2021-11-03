package com.skyxairlines.services.flightquery.api.handlers;

import com.skyxairlines.services.flightquery.api.events.FlightCreatedEvent;
import com.skyxairlines.services.flightquery.infrastructure.entities.Flight;
import com.skyxairlines.services.flightquery.infrastructure.repositories.FlightRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class FlightHandler {

  private final FlightRepository flightRepository;

  public FlightHandler(FlightRepository flightRepository) {
    this.flightRepository = flightRepository;
  }

  @EventHandler
  public void on(FlightCreatedEvent event) {

    System.out.println("Event Handler: " + event.getKey().toString());

    Flight flight = new Flight();
    flight.setAirlineCode(event.getKey().getAirlineCode());
    flight.setFlightNumber(event.getKey().getFlightNumber());
    flight.setOrigin(event.getKey().getOrigin());
    flight.setDepartureDate(event.getKey().getDepartureDate());

    flight.setId(
        String.format("%s.%s.%s.%s",
            flight.getAirlineCode(),
            flight.getFlightNumber(),
            flight.getOrigin(),
            flight.getDepartureDate())
    );

    flightRepository.save(flight);
  }
}
