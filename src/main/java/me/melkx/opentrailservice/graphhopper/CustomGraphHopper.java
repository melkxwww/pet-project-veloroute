package me.melkx.opentrailservice.graphhopper;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.Profile;
import com.graphhopper.routing.WeightingFactory;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.util.PMap;

public class CustomGraphHopper extends GraphHopper {
    @Override
    protected WeightingFactory createWeightingFactory() {
        return (profile, pMap, b) -> {
            if(profile.getName().equals(CustomWeighting.NAME))
                return new CustomWeighting();

            return CustomGraphHopper.super.createWeighting(profile, pMap, b);
        };
    }
}
