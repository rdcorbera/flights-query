package com.skyxairlines.services.flightquery.api.projections;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LegResponse {
  private String origin;
  private String destination;
  private LocalDateTime departureDateTime;
  private LocalDateTime arrivalDateTime;
}
