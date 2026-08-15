package me.melkx.opentrailservice.graphhopper;

import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.util.EdgeIteratorState;

public class CustomWeighting implements Weighting {
    public static final String NAME = "custom_weighting";

    private static final double AVG_BICYCLE_SPEED = 22 / 3.6;
    private static final double MIN_WEIGHT_PER_DISTANCE = 1 / AVG_BICYCLE_SPEED;

    @Override
    public double calcMinWeightPerDistance() {
        // Минимально возможный штраф за единицу дистанции
        return MIN_WEIGHT_PER_DISTANCE;
    }

    @Override
    public double calcEdgeWeight(EdgeIteratorState edge, boolean reverse) {
        // Расчет штрафа текущей дороги
         Tole
        return 0;
    }

    @Override
    public long calcEdgeMillis(EdgeIteratorState edge, boolean reverse) {
        // Столько времени "в пути" по данной дороге в миллисекундах
        return 0;
    }

    @Override
    public double calcTurnWeight(int i, int i1, int i2) {
        // Расчет штрафа за повороты
        return 0;
    }

    @Override
    public long calcTurnMillis(int i, int i1, int i2) {
        // Тоже повороты
        return 0;
    }

    @Override
    public boolean hasTurnCosts() {
        // Штрафовать ли за повороты
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
