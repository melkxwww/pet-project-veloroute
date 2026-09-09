package me.melkx.veloroute.dto.response;

import me.melkx.veloroute.dto.PointDto;

import java.util.List;

public record RouteGenerationResponse(double distanceKm, double timeSeconds, List<PointDto> routePoints, List<Instruction> instructions) {
}
