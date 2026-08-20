package me.melkx.veloroute.dto.response;

import me.melkx.veloroute.dto.Point;

import java.util.List;

public record RouteGenerationResponse(double distanceKm, double timeSeconds, List<Point> routePoints, List<Instruction> instructions) {
}
