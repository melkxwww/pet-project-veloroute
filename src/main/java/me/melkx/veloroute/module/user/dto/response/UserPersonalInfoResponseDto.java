package me.melkx.veloroute.module.user.dto.response;

public record UserPersonalInfoResponseDto(
        Long id,
        String email,
        Boolean activated) {
}
