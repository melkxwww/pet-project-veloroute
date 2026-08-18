package me.melkx.veloroute.graphhopper;

import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.util.EdgeIteratorState;
import me.melkx.veloroute.controller.dto.request.RouteWeightingSettings;

public class CustomWeighting implements Weighting {
    public static final String NAME = "custom_weighting";

    private final RouteWeightingSettings settings;
    private final RequiredEncodedValues encodedValues;

    public CustomWeighting(RouteWeightingSettings settings, RequiredEncodedValues encodedValues) {
        this.settings = settings;
        this.encodedValues = encodedValues;
    }

    @Override
    public double calcMinWeightPerDistance() {
        return 0;
    }

    @Override
    public double calcEdgeWeight(EdgeIteratorState edgeIteratorState, boolean b) {
        return 0;
    }

    @Override
    public long calcEdgeMillis(EdgeIteratorState edgeIteratorState, boolean b) {
        return 0;
    }

    @Override
    public double calcTurnWeight(int i, int i1, int i2) {
        return 0;
    }

    @Override
    public long calcTurnMillis(int i, int i1, int i2) {
        return 0;
    }

    @Override
    public boolean hasTurnCosts() {
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
