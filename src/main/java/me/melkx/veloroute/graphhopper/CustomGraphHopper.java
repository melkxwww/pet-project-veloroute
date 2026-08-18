package me.melkx.veloroute.graphhopper;

import com.graphhopper.GraphHopper;
import com.graphhopper.routing.WeightingFactory;
import com.graphhopper.routing.ev.DecimalEncodedValueImpl;
import com.graphhopper.routing.ev.EncodedValue;
import com.graphhopper.routing.ev.ImportUnit;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.PMap;
import me.melkx.veloroute.controller.dto.request.RouteWeightingSettings;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomGraphHopper extends GraphHopper {
    public static final String ROUTE_SETTINGS_HINT_KEY = "route_settings";

    private static final String SCENIC_FACTOR_KEY = "scenic_factor";
    private static final String SHADE_FACTOR_KEY = "shade_factor";
    private static final String SURFACE_QUALITY_KEY = "surface_quality";
    private static final String TRAFFIC_STRESS_KEY = "traffic_stress";

    @Override
    protected WeightingFactory createWeightingFactory() {
        return (profile, pMap, b) -> {
            if (Objects.equals(profile.getWeighting(), CustomWeighting.NAME)) {
                RouteWeightingSettings settings = pMap.getObject(ROUTE_SETTINGS_HINT_KEY, null);

                RequiredEncodedValues encodedValues = new RequiredEncodedValues(
                        encodingManager.getDecimalEncodedValue(SCENIC_FACTOR_KEY),
                        encodingManager.getDecimalEncodedValue(SHADE_FACTOR_KEY),
                        encodingManager.getDecimalEncodedValue(SURFACE_QUALITY_KEY),
                        encodingManager.getDecimalEncodedValue(TRAFFIC_STRESS_KEY)
                );

                return new CustomWeighting(
                        Objects.requireNonNull(settings, ROUTE_SETTINGS_HINT_KEY + " cannot be null or absent in hints"),
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

        builder.add(new DecimalEncodedValueImpl(SCENIC_FACTOR_KEY, bits, factor, false));
        builder.add(new DecimalEncodedValueImpl(SHADE_FACTOR_KEY, bits, factor, false));
        builder.add(new DecimalEncodedValueImpl(SURFACE_QUALITY_KEY, bits, factor, false));
        builder.add(new DecimalEncodedValueImpl(TRAFFIC_STRESS_KEY, bits, factor, false));

        EncodingManager defaultEm = super.buildEncodingManager(encodedValuesWithProps, activeImportUnits, restrictionVehicleTypesByProfile);

        for (EncodedValue ev : defaultEm.getEncodedValues()) {
            builder.add(ev);
        }

        return builder.build();
    }
}
