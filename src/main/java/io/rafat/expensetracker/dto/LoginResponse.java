package io.rafat.expensetracker.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record LoginResponse(
        JwtToken accessToken,
        JwtToken refreshToken
) {
}
