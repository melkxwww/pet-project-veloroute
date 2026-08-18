package me.melkx.veloroute.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CommonRouteSettings(
        @NotNull @Valid Point startPoint,
        @NotNull @Min(5) @Max(100) Integer targetDistanceKm,
        @NotNull Boolean enableWaterStops,
        @NotNull Boolean enableWindRouting,
        @NotNull @Valid RouteWeightingSettings weightingSettings) {
}
