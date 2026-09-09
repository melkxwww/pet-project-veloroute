package me.melkx.veloroute.route.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import me.melkx.veloroute.core.model.Point;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RouteGenerationRequestDto(
        @NotNull Long presetId,
        @NotNull Long zoneId,
        @NotNull @Valid Point startPoint) {
}
