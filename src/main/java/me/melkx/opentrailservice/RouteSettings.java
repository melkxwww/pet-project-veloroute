package me.melkx.opentrailservice;

import com.graphhopper.GHRequest;
import lombok.AllArgsConstructor;
import me.melkx.opentrailservice.stereotype.*;

@AllArgsConstructor
public class RouteSettings {
    private final Point startPoint;
    private final RouteType routeType;
    private final int maxStartDeviation;
    private final boolean excludeStartDeadEnd;
    private final boolean startingInRouteCenter;
    private final int targetDistance;
    private final int minAltitudeGain;
    private final int maxGradient;
    private final int tortuosityCoefficient;
    private final TerrainPreference terrainPreference;
    private final SurfaceType surfaceType;
    private final boolean allowOffRoad;
    private final TrafficTolerance trafficTolerance;
    private final RoadQuality roadQuality;
    private final boolean excludeToll;
    private final InterestType[] interestType;
    private final int poiCount;
    private final int poiDensity;
    private final POIPriority poiPriority;
    private final SkillLevel skillLevel;
    private final int seed;
    private final ChaosLevel chaosLevel;
    private final Season season;
}
