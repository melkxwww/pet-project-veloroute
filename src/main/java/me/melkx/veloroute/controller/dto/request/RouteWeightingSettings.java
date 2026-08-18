package me.melkx.veloroute.controller.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import me.melkx.veloroute.enums.ElevationPreference;

public record RouteWeightingSettings(
        @NotNull @Min(0) @Max(100) Integer surfaceMinQuality,
        @NotNull @Min(0) @Max(100) Integer maxTrafficStress,
        @NotNull @Min(0) @Max(20) Integer maxIncline,
        @NotNull @Min(0) @Max(100) Integer scenicWeight,
        @NotNull @Min(0) @Max(100) Integer shadeWeight,
        @NotNull @Min(0) @Max(100) Integer explorationWeight,
        @NotNull ElevationPreference elevationPreference,
        @NotNull Boolean isNight,
        @NotNull @Min(30) @Max(150) Integer totalMassKg) {
}
