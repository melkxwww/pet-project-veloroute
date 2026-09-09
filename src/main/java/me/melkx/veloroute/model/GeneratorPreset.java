package me.melkx.veloroute.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.melkx.veloroute.enums.RouteType;
import org.locationtech.jts.geom.Point;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneratorPreset(RouteType routeType,
                              Point targetPoint,
                              Integer desiredDistanceKm,
                              GeneratorPreferences preferences,
                              GeneratorWeights weights) {
}
