package me.melkx.veloroute.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import me.melkx.veloroute.model.Point;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RouteGenerationRequestDto(
        @NotNull Long presetId,
        @NotNull Long zoneId,
        @NotNull @Valid Point startPoint) {
}
