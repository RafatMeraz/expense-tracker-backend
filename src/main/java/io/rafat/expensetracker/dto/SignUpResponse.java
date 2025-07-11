package io.rafat.expensetracker.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record SignUpResponse(
        JwtToken accessToken,
        JwtToken refreshToken,
        UsersResponse user
) {
}
