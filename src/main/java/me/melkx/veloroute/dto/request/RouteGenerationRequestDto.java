package me.melkx.veloroute.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import me.melkx.veloroute.dto.PointDto;

import java.util.List;

@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "route_type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = P2PRouteGenerationRequestDto.class, name = "P2P"),
        @JsonSubTypes.Type(value = LoopRouteGenerationRequestDto.class, name = "LOOP")
})
public abstract class RouteGenerationRequestDto {
    @NotNull
    private final String routeType;

    @NotNull
    @Valid
    private final PointDto startPoint;

    @NotNull
    @Min(1)
    @Max(300)
    private final Double distanceKm;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private final List<@Min(1) @Max(Long.MAX_VALUE) Long> osmBoundaryIds;

    @NotNull
    @Valid
    private final RouteGenerationPreferences preferences;

    @NotNull
    @Valid
    private final RouteGenerationWeights weights;

    protected RouteGenerationRequestDto(
            String routeType,
            PointDto startPoint,
            Double distanceKm,
            List<Integer> osmBoundaryIds,
            RouteGenerationPreferences preferences,
            RouteGenerationWeights weights
    ) {
        this.routeType = routeType;
        this.startPoint = startPoint;
        this.distanceKm = distanceKm;
        this.osmBoundaryIds = osmBoundaryIds != null
                ? List.copyOf(osmBoundaryIds)
                : List.of();
        this.preferences = preferences;
        this.weights = weights;
    }
}