package me.melkx.veloroute.service;

import com.graphhopper.GraphHopper;
import com.graphhopper.util.shapes.Polygon;
import me.melkx.veloroute.dto.Point;
import me.melkx.veloroute.dto.request.LoopRouteGenerationRequest;
import me.melkx.veloroute.dto.request.P2PRouteGenerationRequest;
import me.melkx.veloroute.dto.response.RouteGenerationResponse;
import org.geotools.referencing.GeodeticCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteGeneratorService {
    private static final int MAX_SEARCH_ITERATIONS_OF_POINT = 1000;

    private final GraphHopper graphHopper;

    @Autowired
    public RouteGeneratorService(GraphHopper graphHopper) {
        this.graphHopper = graphHopper;
    }

    public RouteGenerationResponse generateP2PRoute(P2PRouteGenerationRequest request) {
        List<Polygon> explorationZoneGeom = request.getExplorationZoneIds().stream().map(this::getExplorationZoneGeometryById)
                .toList();

        List<Point> candidates = new ArrayList<>();

        GeodeticCalculator calculator = new GeodeticCalculator();
        calculator.setStartingGeographicPoint(request.getStartPoint().latitude(), request.getStartPoint().longitude());

        for (int i = 0; i < MAX_SEARCH_ITERATIONS_OF_POINT; i++) {
            double azimuth = Math.random() * 360;
            calculator.setDirection(azimuth, request.getDistanceKm() * 1000);

            Point2D destination = calculator.getDestinationGeographicPoint();
            Point point = new Point(destination.getY(), destination.getX());

            if(!inExplorationZone(explorationZoneGeom, point))
                continue;

            if (!isLand(point))
                continue;

            candidates.add(point);
        }

        return null;
    }

    private boolean inExplorationZone(List<Polygon> explorationZoneGeom, Point point) {
        return explorationZoneGeom.stream().anyMatch(zone -> zone.contains(point.latitude(), point.longitude()));
    }

    private boolean isLand(Point point) {

    }

    public RouteGenerationResponse generateLoopRoute(LoopRouteGenerationRequest request) {
        return null;
    }

    private Polygon getExplorationZoneGeometryById(Integer explorationZoneId) {
        // TODO: GET EXPLORATION ZONE FROM <POST GIS>
        return null;
    }
}
