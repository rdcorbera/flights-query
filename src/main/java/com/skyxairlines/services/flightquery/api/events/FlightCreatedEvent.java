package com.skyxairlines.services.flightquery.api.events;

import com.skyxairlines.services.flightquery.business.domain.FlightKey;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightCreatedEvent {
  private FlightKey key;
}
