package me.melkx.veloroute.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.melkx.veloroute.dto.Point;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class P2PRouteGenerationRequest extends RouteGenerationRequest {
    protected P2PRouteGenerationRequest(String routeType, Point startPoint, Double distanceKm, List<Integer> explorationZoneIds, RouteGenerationPreferences preferences, RouteGenerationWeights weights) {
        super(routeType, startPoint, distanceKm, explorationZoneIds, preferences, weights);
    }
}
