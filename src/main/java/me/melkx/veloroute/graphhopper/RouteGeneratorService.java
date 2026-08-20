package me.melkx.veloroute.graphhopper;

import com.graphhopper.GraphHopper;
import me.melkx.veloroute.dto.request.LoopRouteGenerationRequest;
import me.melkx.veloroute.dto.request.P2PRouteGenerationRequest;
import me.melkx.veloroute.dto.request.RouteGenerationRequest;
import me.melkx.veloroute.dto.response.RouteGenerationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteGeneratorService {
    private final GraphHopper graphHopper;

    @Autowired
    public RouteGeneratorService(GraphHopper graphHopper) {
        this.graphHopper = graphHopper;
    }

    public RouteGenerationResponse generateP2PRoute(P2PRouteGenerationRequest request) {
        return null;
    }

    public RouteGenerationResponse generateLoopRoute(LoopRouteGenerationRequest request) {
        return null;
    }
}
