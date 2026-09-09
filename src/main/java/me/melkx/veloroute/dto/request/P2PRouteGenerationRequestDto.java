package me.melkx.veloroute.dto.request;

import lombok.*;
import me.melkx.veloroute.dto.PointDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class P2PRouteGenerationRequestDto extends RouteGenerationRequestDto {
    protected P2PRouteGenerationRequestDto(String routeType, PointDto startPoint, Double distanceKm, List<Integer> explorationZoneIds, RouteGenerationPreferences preferences, RouteGenerationWeights weights) {
        super(routeType, startPoint, distanceKm, explorationZoneIds, preferences, weights);
    }
}
