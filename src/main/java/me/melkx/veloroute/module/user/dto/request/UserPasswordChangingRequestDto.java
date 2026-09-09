package me.melkx.veloroute.module.user.dto.request;

import jakarta.validation.constraints.NotNull;
import me.melkx.veloroute.module.user.util.Password;

public record UserPasswordChangingRequestDto(
        @NotNull Long userId,
        @NotNull @Password String oldPassword,
        @NotNull @Password String newPassword) {
}
