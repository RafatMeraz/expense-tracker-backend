package io.rafat.expensetracker.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record JwtToken (String token, Long expiration){
}
