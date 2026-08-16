package me.melkx.opentrailservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.melkx.opentrailservice.enums.GravelSize;
import me.melkx.opentrailservice.enums.RoadProfileType;
import me.melkx.opentrailservice.enums.SurfaceType;

@AllArgsConstructor
@Getter
public class WeightingSettings {
    private final int maxGradient;
    private final double userAverageSpeed;
    private final double trafficIntensity;
    private final double picturesqueness;
    private final double shadiness;
    private final boolean excludeHighway;
    private final boolean considerWind;
    private final GravelSize gravelSize;
    private final RoadProfileType roadProfileType;
    private final SurfaceType surfaceType;
}
