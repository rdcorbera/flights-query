package com.skyxairlines.services.flightquery.api.handlers;

import com.skyxairlines.libs.flightsdomain.events.FlightCreatedEvent;
import com.skyxairlines.libs.flightsdomain.events.GateUpdatedEvent;
import com.skyxairlines.libs.flightsdomain.events.LegAddedEvent;
import com.skyxairlines.services.flightquery.infrastructure.entities.Flight;
import com.skyxairlines.services.flightquery.infrastructure.entities.Leg;
import com.skyxairlines.services.flightquery.infrastructure.repositories.FlightRepository;
import com.skyxairlines.services.flightquery.infrastructure.repositories.LegRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class FlightHandler {

  private final FlightRepository flightRepository;
  private final LegRepository legRepository;

  public FlightHandler(
      FlightRepository flightRepository,
      LegRepository legRepository) {
    this.flightRepository = flightRepository;
    this.legRepository = legRepository;
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

  @EventHandler
  public void on(LegAddedEvent event) {
    System.out.println("LegAddedEvent " + event.getFlightId());
    Flight flight = flightRepository.getById(event.getFlightId());
    Leg newLeg = Leg.builder()
        .origin(event.getOrigin())
        .destination(event.getDestination())
        .departureDateTime(event.getDepartureDateTime())
        .arrivalDateTime(event.getArrivalDateTime())
        .flight(flight)
        .build();

    legRepository.save(newLeg);
  }

  @EventHandler
  public void on(GateUpdatedEvent event) {
    System.out.println("GateUpdatedEvent " + event.getFlightId());

    Flight flight = flightRepository.getById(event.getFlightId());
    Leg leg = flight.getLegs().stream()
        .filter(l -> l.getOrigin().equals(event.getOrigin()))
        .findFirst()
        .get();

    leg.setGate(event.getGate());
    leg.setTerminal(event.getTerminal());

    flightRepository.save(flight);
  }
}
