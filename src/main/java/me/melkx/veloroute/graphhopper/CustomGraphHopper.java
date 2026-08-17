package me.melkx.veloroute.graphhopper;

import com.graphhopper.GraphHopper;
import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.*;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.parsers.TagParser;
import com.graphhopper.storage.IntsRef;
import com.graphhopper.util.PMap;

import java.util.List;
import java.util.Map;

public class CustomGraphHopper extends GraphHopper {
    @Override
    protected EncodingManager buildEncodingManager(Map<String, PMap> encodedValuesWithProps, Map<String, ImportUnit> activeImportUnits, Map<String, List<String>> restrictionVehicleTypesByProfile) {
        DecimalEncodedValue scenicEv = new DecimalEncodedValueImpl("scenic_factor")
        TagParser scenicParser = new TagParser() {
            @Override
            public void handleWayTags(int i, EdgeIntAccess edgeIntAccess, ReaderWay readerWay, IntsRef intsRef) {

            }
        };

        return super.buildEncodingManager(encodedValuesWithProps, activeImportUnits, restrictionVehicleTypesByProfile);
    }
}
