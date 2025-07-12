package io.rafat.expensetracker.dto.bank_card;

import lombok.Builder;

@Builder(toBuilder = false)
public record BankCardTypeResponse(Long id, String name, String value) {
}
