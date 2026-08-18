package me.melkx.veloroute.controller;

import jakarta.validation.Valid;
import me.melkx.veloroute.controller.dto.request.RouteSettingsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/route")
public class RouteGeneratorController {
    @PostMapping
    public ResponseEntity<?> generateRoute(@Valid @RequestBody RouteSettingsRequest settingsRequest) {

    }
}
