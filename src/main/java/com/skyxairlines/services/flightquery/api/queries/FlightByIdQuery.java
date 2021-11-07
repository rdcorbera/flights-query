package com.skyxairlines.services.flightquery.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightByIdQuery {
  private String flightId;
}
