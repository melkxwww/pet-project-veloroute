package me.melkx.veloroute.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import me.melkx.veloroute.core.enums.RouteType;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneratorPreset(@NotNull RouteType routeType,
                              Point targetPoint,
                              @NotNull Integer desiredDistanceKm,
                              @NotNull @Valid GeneratorPreferences preferences,
                              @NotNull @Valid GeneratorWeights weights) {
}
