package me.melkx.veloroute.graphhopper;

import com.graphhopper.GraphHopper;
import com.graphhopper.routing.WeightingFactory;
import com.graphhopper.routing.ev.*;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.PMap;
import me.melkx.veloroute.enums.SurfaceType;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomGraphHopper extends GraphHopper {
    public static final String CUSTOM_WEIGHTING_SETTINGS_HINT_KEY = "custom_weighting_settings";

    private static final String PICTURESQUENESS_KEY = "scenic_factor";
    private static final String SHADINESS_KEY = "shade_factor";
    private static final String ROAD_QUALITY_KEY = "surface_quality";
    private static final String TRAFFIC_STRESS_KEY = "traffic_stress";
    private static final String ILLUMINATION_STRESS_KEY = "traffic_stress";
    private static final String SURFACE_TYPE_KEY = "traffic_stress";

    @Override
    protected WeightingFactory createWeightingFactory() {
        return (profile, pMap, b) -> {
            if (Objects.equals(profile.getWeighting(), CustomWeighting.NAME)) {
                CustomWeighting.Settings settings = pMap.getObject(CUSTOM_WEIGHTING_SETTINGS_HINT_KEY, null);

                CustomWeighting.RequiredEncodedValues encodedValues = new CustomWeighting.RequiredEncodedValues(
                        encodingManager.getDecimalEncodedValue(PICTURESQUENESS_KEY),
                        encodingManager.getDecimalEncodedValue(SHADINESS_KEY),
                        encodingManager.getDecimalEncodedValue(ROAD_QUALITY_KEY),
                        encodingManager.getDecimalEncodedValue(TRAFFIC_STRESS_KEY),
                        encodingManager.getDecimalEncodedValue(ILLUMINATION_STRESS_KEY),
                        encodingManager.getEnumEncodedValue(SURFACE_TYPE_KEY, SurfaceType.class),
                        encodingManager.getDecimalEncodedValue(AverageSlope.KEY)
                );

                return new CustomWeighting(
                        Objects.requireNonNull(settings, CUSTOM_WEIGHTING_SETTINGS_HINT_KEY + " cannot be null or absent in hints"),
                        encodedValues
                );
            }

            return CustomGraphHopper.super.createWeighting(profile, pMap, b);
        };
    }

    @Override
    protected EncodingManager buildEncodingManager(
            Map<String, PMap> encodedValuesWithProps,
            Map<String, ImportUnit> activeImportUnits,
            Map<String, List<String>> restrictionVehicleTypesByProfile) {
        EncodingManager.Builder builder = new EncodingManager.Builder();

        int bits = 5;
        double factor = 1.0 / ((1 << bits) - 1);

        builder.add(new DecimalEncodedValueImpl(PICTURESQUENESS_KEY, bits, factor, false));
        builder.add(new DecimalEncodedValueImpl(SHADINESS_KEY, bits, factor, false));
        builder.add(new DecimalEncodedValueImpl(ROAD_QUALITY_KEY, bits, factor, false));
        builder.add(new DecimalEncodedValueImpl(TRAFFIC_STRESS_KEY, bits, factor, false));
        builder.add(new DecimalEncodedValueImpl(ILLUMINATION_STRESS_KEY, bits, factor, false));
        builder.add(new EnumEncodedValue<>(SURFACE_TYPE_KEY, SurfaceType.class));

        EncodingManager defaultEm = super.buildEncodingManager(encodedValuesWithProps, activeImportUnits, restrictionVehicleTypesByProfile);

        for (EncodedValue ev : defaultEm.getEncodedValues()) {
            builder.add(ev);
        }

        return builder.build();
    }
}
