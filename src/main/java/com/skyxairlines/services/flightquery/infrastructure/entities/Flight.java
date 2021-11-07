package com.skyxairlines.services.flightquery.infrastructure.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Flight {
  @Id
  private String id;
  private String airlineCode;
  private int flightNumber;
  private String origin;
  private LocalDate departureDate;
  @OneToMany(mappedBy = "flight")
  private List<Leg> legs;
}
