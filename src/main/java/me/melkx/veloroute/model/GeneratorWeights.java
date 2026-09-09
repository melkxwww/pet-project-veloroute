package me.melkx.veloroute.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneratorWeights(
        @DecimalMin("0") @DecimalMax("1") @NotNull Double picturesquenessWeight,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double shadinessWeight,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double roadQualityWeight,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double trafficStressWeight,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double illuminationWeight,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double surfaceTypeWeight,
        @DecimalMin("0") @DecimalMax("1") @NotNull Double averageSlopeWeight) {
}