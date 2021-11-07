package com.skyxairlines.services.flightquery.api.projections;

import com.skyxairlines.services.flightquery.api.queries.FlightByIdQuery;
import com.skyxairlines.services.flightquery.infrastructure.entities.Flight;
import com.skyxairlines.services.flightquery.infrastructure.repositories.FlightRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class FlightProjection {
  private final FlightRepository flightRepository;

  public FlightProjection(FlightRepository flightRepository) {
    this.flightRepository = flightRepository;
  }

  @QueryHandler
  public FlightResponse handle(FlightByIdQuery query) {
    Flight flight = flightRepository.findById(query.getFlightId()).get();
    FlightResponse response = FlightResponse.builder()
        .id(flight.getId())
        .airlineCode(flight.getAirlineCode())
        .flightNumber(flight.getFlightNumber())
        .origin(flight.getOrigin())
        .departureDate(flight.getDepartureDate())
        .build();

    flight.getLegs().forEach(l -> {
      response.getLegs().add(LegResponse.builder()
          .origin(l.getOrigin())
          .destination(l.getDestination())
          .departureDateTime(l.getDepartureDateTime())
          .arrivalDateTime(l.getArrivalDateTime())
          .build());
    });

    return response;
  }
}
