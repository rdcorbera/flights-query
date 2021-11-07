package com.skyxairlines.services.flightquery.gateway.flight;

import com.skyxairlines.services.flightquery.api.projections.FlightResponse;
import com.skyxairlines.services.flightquery.api.queries.FlightByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/flights")
@RestController
public class FlightController {

  private final QueryGateway queryGateway;

  public FlightController(QueryGateway queryGateway) {
    this.queryGateway = queryGateway;
  }

  @GetMapping("/{flightId}")
  public CompletableFuture<FlightResponse> retrieveById(@PathVariable String flightId) {
    FlightByIdQuery query = new FlightByIdQuery(flightId);

    return queryGateway.query(query,  ResponseTypes.instanceOf(FlightResponse.class));
  }
}
