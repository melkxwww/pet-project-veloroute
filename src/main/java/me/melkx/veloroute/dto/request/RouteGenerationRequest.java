package me.melkx.veloroute.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import me.melkx.veloroute.dto.Point;

import java.util.List;

@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "route_type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = P2PRouteGenerationRequest.class, name = "P2P"),
        @JsonSubTypes.Type(value = LoopRouteGenerationRequest.class, name = "LOOP")
})
public abstract class RouteGenerationRequest {

    @NotNull
    private final String routeType;

    @NotNull
    @Valid
    private final Point startPoint;

    @NotNull
    @Min(1)
    @Max(300)
    private final Double distanceKm;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private final List<@Min(1) @Max(Integer.MAX_VALUE) Integer> explorationZoneIds;

    @NotNull
    @Valid
    private final RouteGenerationPreferences preferences;

    @NotNull
    @Valid
    private final RouteGenerationWeights weights;

    protected RouteGenerationRequest(
            String routeType,
            Point startPoint,
            Double distanceKm,
            List<Integer> explorationZoneIds,
            RouteGenerationPreferences preferences,
            RouteGenerationWeights weights
    ) {
        this.routeType = routeType;
        this.startPoint = startPoint;
        this.distanceKm = distanceKm;
        this.explorationZoneIds = explorationZoneIds != null
                ? List.copyOf(explorationZoneIds)
                : List.of();
        this.preferences = preferences;
        this.weights = weights;
    }
}