package me.melkx.veloroute.graphhopper;

import com.graphhopper.routing.ev.DecimalEncodedValue;
import com.graphhopper.routing.ev.EnumEncodedValue;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.util.EdgeIteratorState;
import me.melkx.veloroute.enums.SurfaceType;

public class CustomWeighting implements Weighting {
    public static final String NAME = "custom_weighting";

    private final Settings settings;
    private final RequiredEncodedValues encodedValues;

    public CustomWeighting(Settings settings, RequiredEncodedValues encodedValues) {
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

    public record Settings(double picturesqueness,
                           double shadiness,
                           double roadQuality,
                           double trafficStress,
                           double explorationImportant,
                           double illumination,
                           int averageSlope,
                           SurfaceType surfaceType) {
    }

    public record RequiredEncodedValues(DecimalEncodedValue picturesquenessEv,
                                        DecimalEncodedValue shadinessEv,
                                        DecimalEncodedValue roadQualityEv,
                                        DecimalEncodedValue trafficStressEv,
                                        DecimalEncodedValue illuminationImportantEv,
                                        EnumEncodedValue<SurfaceType> surfaceTypeEv,
                                        DecimalEncodedValue averageSlopeEv) {
    }
}