package io.rafat.expensetracker.dto.bank_card;

import lombok.Builder;

@Builder(toBuilder = true)
public record BankCardResponse(String holderName,
                               String cardNo,
                               BankCardTypeResponse cardType) {
}
