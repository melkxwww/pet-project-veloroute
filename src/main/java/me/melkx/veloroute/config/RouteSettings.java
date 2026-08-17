package me.melkx.veloroute.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import me.melkx.veloroute.enums.ElevationPreference;
import me.melkx.veloroute.enums.RouteType;

public record RouteSettings(
        @NotNull @Min(5) @Max(100) int targetDistanceKm,
        @NotNull RouteType routeType,
        @Null @Min(0) @Max(180) int loopDirectionDeg,
        @Null Boolean loopUniqueness,
        @NotNull @Min(0) @Max(100) int surfaceMinQuality,
        @NotNull @Min(0) @Max(100) int maxTrafficStress,
        @NotNull @Min(0) @Max(20) int maxIncline,
        @NotNull @Min(0) @Max(100) int scenicWeight,
        @NotNull @Min(0) @Max(100) int shadeWeight,
        @NotNull @Min(0) @Max(100) int explorationWeight,
        @NotNull ElevationPreference elevationPreference,
        @NotNull boolean enableWaterStops,
        @NotNull boolean enableWindRouting,
        @NotNull boolean isNight,
        @NotNull @Min(30) @Max(150) int totalMassKg) {
}
