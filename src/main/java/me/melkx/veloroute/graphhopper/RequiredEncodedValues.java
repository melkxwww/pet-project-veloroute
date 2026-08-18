package me.melkx.veloroute.graphhopper;

import com.graphhopper.routing.ev.DecimalEncodedValue;

public record RequiredEncodedValues(DecimalEncodedValue scenicFactorEv,
                                    DecimalEncodedValue shadeFactorEv,
                                    DecimalEncodedValue surfaceQualityEv,
                                    DecimalEncodedValue trafficStressEv) {
}
