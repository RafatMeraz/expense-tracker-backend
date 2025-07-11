package io.rafat.expensetracker.dto;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder(toBuilder = true)
public record ErrorResponse(String message, List<Map<String, String>> errors) {
}
