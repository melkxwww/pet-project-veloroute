package me.melkx.veloroute.graphhopper;

import com.graphhopper.routing.ev.DecimalEncodedValue;
import com.graphhopper.routing.ev.EnumEncodedValue;
import com.graphhopper.routing.ev.RoadClass;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.util.EdgeIteratorState;
import me.melkx.veloroute.enums.SurfaceType;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class CustomWeighting implements Weighting {
    public static final String NAME = "custom_weighting";

    private static final double MIN_MULTIPLIER = 1;
    private static final double MAX_AVERAGE_SLOPE = 45;

    private final Preferences preferences;
    private final Weights weights;
    private final RequiredEncodedValues encodedValues;
    @Nullable
    private final List<Integer> blockedEdges;

    public CustomWeighting(Preferences preferences, Weights weights, @Nullable List<Integer> blockedEdges, RequiredEncodedValues encodedValues) {
        this.preferences = preferences;
        this.weights = weights;
        this.blockedEdges = blockedEdges;
        this.encodedValues = encodedValues;
    }

    @Override
    public double calcMinWeightPerDistance() {
        return MIN_MULTIPLIER;
    }

    @Override
    public double calcEdgeWeight(EdgeIteratorState edge, boolean reverse) {
        double distanceMeters = edge.getDistance();
        if (distanceMeters < 0)
            return 0;

        if (isEdgeBlocked(edge.getEdge()))
            return Double.POSITIVE_INFINITY;

        if (isUnavailableRoad(edge.get(encodedValues.roadClassEv())))
            return Double.POSITIVE_INFINITY;

        double multiplier = MIN_MULTIPLIER +
                calcPicturesquenessMultiplier(edge.get(encodedValues.picturesquenessEv())) +
                calcShadinessMultiplier(edge.get(encodedValues.shadinessEv())) +
                calcRoadQualityMultiplier(edge.get(encodedValues.roadQualityEv())) +
                calcTrafficStressMultiplier(edge.get(encodedValues.trafficStressEv())) +
                calcIlluminationMultiplier(edge.get(encodedValues.illuminationEv())) +
                calcSurfaceTypesMultiplier(edge.get(encodedValues.surfaceTypeEv())) +
                calcAverageSlopeMultiplier(edge.get(encodedValues.averageSlopeEv()));

        return distanceMeters * multiplier;
    }

    private boolean isEdgeBlocked(int edgeId) {
        return blockedEdges != null && blockedEdges.contains(edgeId);
    }

    private boolean isUnavailableRoad(RoadClass roadClass) {
        return roadClass == RoadClass.MOTORWAY || roadClass == RoadClass.STEPS;
    }

    private double calcPicturesquenessMultiplier(double picturesqueness) {
        return Math.abs(picturesqueness - preferences.picturesqueness()) * weights.picturesquenessWeight();
    }

    private double calcShadinessMultiplier(double shadiness) {
        return Math.abs(shadiness - preferences.shadiness()) * weights.shadinessWeight();
    }

    private double calcRoadQualityMultiplier(double roadQuality) {
        return Math.abs(roadQuality - preferences.roadQuality()) * weights.roadQualityWeight();
    }

    private double calcTrafficStressMultiplier(double trafficStress) {
        return Math.abs(trafficStress - preferences.trafficStress()) * weights.trafficStressWeight();
    }

    private double calcIlluminationMultiplier(double illumination) {
        return Math.abs(illumination - preferences.illumination()) * weights.illuminationWeight();
    }

    private double calcSurfaceTypesMultiplier(SurfaceType surfaceType) {
        for (SurfaceType s : preferences.surfaceTypes()) {
            if (s == surfaceType) {
                return 0;
            }
        }
        return weights.surfaceTypeWeight();
    }

    private double calcAverageSlopeMultiplier(double averageSlope) {
        return (Math.abs(averageSlope - preferences.averageSlope()) / MAX_AVERAGE_SLOPE) * weights.averageSlopeWeight();
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
        return NAME;
    }

    public record Preferences(double picturesqueness,
                              double shadiness,
                              double roadQuality,
                              double trafficStress,
                              double illumination,
                              SurfaceType[] surfaceTypes,
                              double averageSlope) {
    }

    public record Weights(double picturesquenessWeight,
                          double shadinessWeight,
                          double roadQualityWeight,
                          double trafficStressWeight,
                          double illuminationWeight,
                          double surfaceTypeWeight,
                          double averageSlopeWeight) {
    }

    public record RequiredEncodedValues(DecimalEncodedValue picturesquenessEv,
                                        DecimalEncodedValue shadinessEv,
                                        DecimalEncodedValue roadQualityEv,
                                        DecimalEncodedValue trafficStressEv,
                                        DecimalEncodedValue illuminationEv,
                                        EnumEncodedValue<SurfaceType> surfaceTypeEv,
                                        DecimalEncodedValue averageSlopeEv,
                                        EnumEncodedValue<RoadClass> roadClassEv) {
    }
}