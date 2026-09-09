package me.melkx.veloroute.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.melkx.veloroute.enums.SurfaceType;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneratorPreferences(double picturesqueness,
                          double shadiness,
                          double roadQuality,
                          double trafficStress,
                          double illumination,
                          SurfaceType[] surfaceTypes,
                          double averageSlope) {
}
