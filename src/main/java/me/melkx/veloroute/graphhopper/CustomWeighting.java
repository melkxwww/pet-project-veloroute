package me.melkx.veloroute.graphhopper;

import com.graphhopper.routing.ev.EnumEncodedValue;
import com.graphhopper.routing.ev.RoadClass;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.util.EdgeIteratorState;

public class CustomWeighting implements Weighting {
    public static final String NAME = "custom_weighting";

    private final WeightingSettings weightingSettings;
    private final EnumEncodedValue<RoadClass> roadClassEncodedValue;

    private double cachedMinWeightPerDistance;

    public CustomWeighting(WeightingSettings weightingSettings, EnumEncodedValue<RoadClass> roadClassEncodedValue) {
        this.weightingSettings = weightingSettings;
        this.roadClassEncodedValue = roadClassEncodedValue;
        initCached();
    }

    private void initCached() {
        cachedMinWeightPerDistance = 1 / (weightingSettings.getUserAverageSpeed() / 3.6);
    }

    @Override
    public double calcMinWeightPerDistance() {
        return cachedMinWeightPerDistance;
    }

    @Override
    public double calcEdgeWeight(EdgeIteratorState edge, boolean reverse) {
        RoadClass roadClass = edge.get(roadClassEncodedValue);
        boolean isHighway = roadClass == RoadClass.MOTORWAY ||
                            roadClass == RoadClass.TRUNK ||
                            roadClass == RoadClass.PRIMARY;
        if (weightingSettings.isExcludeHighway() && isHighway)
            return Double.POSITIVE_INFINITY;

        return 0;
    }

    @Override
    public long calcEdgeMillis(EdgeIteratorState edge, boolean reverse) {
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
        return "";
    }
}
