package me.melkx.opentrailservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.melkx.opentrailservice.enums.GravelSize;
import me.melkx.opentrailservice.enums.RoadProfileType;
import me.melkx.opentrailservice.enums.RouteType;
import me.melkx.opentrailservice.enums.SurfaceType;

@AllArgsConstructor
@Getter
public class RouteSettingsProfile {
    private final int distance;
    private final int maxClimbMeters;
    private final boolean enableCutOffs;
    private final RouteType routeType;
    private final WeightingSettings weightingSettings;
}
