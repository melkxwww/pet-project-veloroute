package me.melkx.veloroute.dto.response;

public record Instruction(int actionId, double distance, double timeSeconds, String roadName) {
}
