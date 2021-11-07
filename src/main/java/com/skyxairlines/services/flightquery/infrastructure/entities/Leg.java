package com.skyxairlines.services.flightquery.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Leg {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String origin;
  private String destination;
  private LocalDateTime departureDateTime;
  private LocalDateTime arrivalDateTime;
  @ManyToOne
  @JoinColumn(name="flight_id", nullable=false)
  private Flight flight;
}
