package com.skyxairlines.services.flightquery.api.projections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class FlightResponse {
  private String id;
  private String airlineCode;
  private int flightNumber;
  private String origin;
  private LocalDate departureDate;
  @Builder.Default
  private List<LegResponse> legs = new ArrayList<>();
}
