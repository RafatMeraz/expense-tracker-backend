package io.rafat.expensetracker.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder(toBuilder = true)
public record ErrorResponse(String message) {
}
