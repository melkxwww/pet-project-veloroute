package me.melkx.veloroute.controller;

import jakarta.validation.Valid;
import me.melkx.veloroute.dto.request.LoopRouteGenerationRequest;
import me.melkx.veloroute.dto.request.P2PRouteGenerationRequest;
import me.melkx.veloroute.dto.request.RouteGenerationRequest;
import me.melkx.veloroute.dto.response.RouteGenerationResponse;
import me.melkx.veloroute.graphhopper.RouteGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {
    private final RouteGeneratorService routeGenerator;

    @Autowired
    public RouteController(RouteGeneratorService routeGenerator) {
        this.routeGenerator = routeGenerator;
    }

    @PostMapping
    public ResponseEntity<RouteGenerationResponse> generateRoute(@Valid @RequestBody RouteGenerationRequest request) {
        RouteGenerationResponse response;
        if(request instanceof P2PRouteGenerationRequest p2p) {
            response = routeGenerator.generateP2PRoute(p2p);
        }
        else if(request instanceof LoopRouteGenerationRequest loop) {
            response = routeGenerator.generateLoopRoute(loop);
        }
        else
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response);
    }
}
