package me.melkx.veloroute.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneratorWeights(double picturesquenessWeight,
                      double shadinessWeight,
                      double roadQualityWeight,
                      double trafficStressWeight,
                      double illuminationWeight,
                      double surfaceTypeWeight,
                      double averageSlopeWeight) {
}