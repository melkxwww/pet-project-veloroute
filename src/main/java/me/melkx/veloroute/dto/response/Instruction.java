package me.melkx.veloroute.dto.response;

public record Instruction(int actionId, double distanceMeters, double timeSeconds, String roadName) {
}
