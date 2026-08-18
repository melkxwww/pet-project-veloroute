package me.melkx.veloroute.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LoopRouteSettingsRequest(
        @NotNull @Valid CommonRouteSettings commonSettings,
        @NotNull @Min(0) @Max(180) Integer loopDirectionDeg,
        @NotNull Boolean loopUniqueness) implements RouteSettingsRequest {
}
