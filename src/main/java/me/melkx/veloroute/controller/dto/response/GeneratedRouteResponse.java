package me.melkx.veloroute.controller.dto.response;

import com.graphhopper.util.PointList;

// IN-PROGRESS
public record GeneratedRouteResponse(
        GeneratedRouteStatus status,
        Integer distanceKm,
        Integer timeMinutes,
        Integer ascendMeters,
        PointList route) {
    public static GeneratedRouteResponse createNotFoundRouteResponse() {
        return new GeneratedRouteResponse(
                GeneratedRouteStatus.NOT_FOUND, null
        );
    }
}
