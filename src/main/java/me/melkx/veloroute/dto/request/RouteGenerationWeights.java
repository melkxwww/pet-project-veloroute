package me.melkx.veloroute.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record RouteGenerationWeights(@NotNull @DecimalMin("0") @DecimalMax("1") Double picturesquenessWeight,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double shadinessWeight,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double roadQualityWeight,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double trafficStressWeight,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double illuminationWeight,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double surfaceTypeWeight,
                                     @NotNull @DecimalMin("0") @DecimalMax("1") Double averageSlopeWeight) {
}
