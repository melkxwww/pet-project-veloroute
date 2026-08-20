package me.melkx.veloroute.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.melkx.veloroute.dto.Point;

@Data
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
    private String routeType;

    @NotNull
    @Valid
    private Point startPoint;

    @NotNull
    @Min(1)
    @Max(300)
    private Double distanceKm;

    @NotNull
    private Integer explorationZoneId;

    @NotNull
    @Valid
    private RouteGenerationPreferences preferences;

    @NotNull
    @Valid
    private RouteGenerationWeights weights;
}
