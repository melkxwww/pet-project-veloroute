package me.melkx.veloroute.module.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import me.melkx.veloroute.module.user.util.Password;

public record UserRegistrationRequestDto(
        @NotNull
        @Email
        String email,
        @NotNull
        @Password
        String password) {
}
