package me.melkx.veloroute.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.melkx.veloroute.model.GeneratorPreset;
import me.melkx.veloroute.model.Point;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RouteGenerationResponseDto(
        Long routeId,
        Double distanceKm,
        List<Point> pathPoints,
        Point startPoint,
        Point endPoint,
        LocalDateTime createdAt,
        GeneratorPreset presetSnapshot)
{}