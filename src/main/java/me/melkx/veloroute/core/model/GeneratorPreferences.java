package me.melkx.veloroute.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import me.melkx.veloroute.core.enums.SurfaceType;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneratorPreferences(
        @DecimalMin("0") @DecimalMax("1") @NotNull Double picturesqueness,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double shadiness,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double roadQuality,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double trafficStress,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double illumination,
        @NotNull SurfaceType[] surfaceTypes,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double averageSlope) {
}
