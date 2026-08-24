package me.melkx.veloroute.dto.request;

import jakarta.validation.constraints.*;
import me.melkx.veloroute.enums.SurfaceType;

import java.util.List;

public record RouteGenerationPreferences(@NotNull @DecimalMin("0") @DecimalMax("1") Double picturesqueness,
                                         @NotNull @DecimalMin("0") @DecimalMax("1") Double shadiness,
                                         @NotNull @DecimalMin("0") @DecimalMax("1") Double roadQuality,
                                         @NotNull @DecimalMin("0") @DecimalMax("1") Double trafficStress,
                                         @NotNull @DecimalMin("0") @DecimalMax("1") Double illumination,
                                         @NotNull List<SurfaceType> surfaceTypes,
                                         @NotNull @DecimalMin("0") @DecimalMax("45") Double averageSlope) {
}
