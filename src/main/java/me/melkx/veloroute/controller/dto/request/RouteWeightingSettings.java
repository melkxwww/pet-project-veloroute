package me.melkx.veloroute.controller.dto.request;

import jakarta.validation.constraints.*;
import me.melkx.veloroute.enums.SurfaceType;

public record RouteWeightingSettings(@NotNull @DecimalMin("0") @DecimalMax("1") Double picturesqueness,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double shadiness,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double roadQuality,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double trafficStress,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double explorationImportant,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double illumination,
                                     @NotNull @Min(0) @Max(45) Integer averageSlope,
                                     @NotNull SurfaceType surfaceType) {
}
