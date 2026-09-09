package me.melkx.veloroute.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import me.melkx.veloroute.dto.PointDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class LoopRouteGenerationRequestDto extends RouteGenerationRequestDto {
    @NotNull
    private final Boolean strictLoop;

    protected LoopRouteGenerationRequestDto(String routeType, PointDto startPoint, Double distanceKm, List<Integer> explorationZoneIds, RouteGenerationPreferences preferences, RouteGenerationWeights weights, Boolean strictLoop) {
        super(routeType, startPoint, distanceKm, explorationZoneIds, preferences, weights);
        this.strictLoop = strictLoop;
    }
}
