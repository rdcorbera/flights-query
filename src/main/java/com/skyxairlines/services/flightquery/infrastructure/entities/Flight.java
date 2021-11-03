package com.skyxairlines.services.flightquery.infrastructure.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Flight {
  @Id
  private String id;
  private String airlineCode;
  private int flightNumber;
  private String origin;
  private LocalDate departureDate;
}
