package me.melkx.veloroute.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class P2PRouteGenerationRequest extends RouteGenerationRequest {
    @NotNull
    private Boolean strictLoop;
}
