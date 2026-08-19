package me.melkx.veloroute.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import me.melkx.veloroute.enums.RouteType;
import me.melkx.veloroute.enums.SurfaceType;

public record RouteSettingsRequest(
        @NotNull RouteType routeType,
        @NotNull Point startPoint,
        @NotNull @Min(1) @Max(300) Integer distanceKm,
        @NotNull Boolean enableWindRouting,
        @NotNull Boolean enableWaterStops,
        @NotNull @Valid RouteWeightingSettings weightingSettings) {
}
