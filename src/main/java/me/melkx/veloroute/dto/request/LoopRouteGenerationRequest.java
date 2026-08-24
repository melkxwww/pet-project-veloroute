package me.melkx.veloroute.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.melkx.veloroute.dto.Point;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class LoopRouteGenerationRequest extends RouteGenerationRequest {
    @NotNull
    private final Boolean strictLoop;

    protected LoopRouteGenerationRequest(String routeType, Point startPoint, Double distanceKm, List<Integer> explorationZoneIds, RouteGenerationPreferences preferences, RouteGenerationWeights weights, Boolean strictLoop) {
        super(routeType, startPoint, distanceKm, explorationZoneIds, preferences, weights);
        this.strictLoop = strictLoop;
    }
}
