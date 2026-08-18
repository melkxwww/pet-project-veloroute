package me.melkx.veloroute.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PointToPointRouteSettingsRequest(
        @NotNull @Valid CommonRouteSettings commonSettings) implements RouteSettingsRequest {
}
