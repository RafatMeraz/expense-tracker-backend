package io.rafat.expensetracker.dto.bank_card;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(toBuilder = true)
public record AddBankCardRequest(@NotBlank(message = "Holder name is required")
                                 String holderName,
                                 @NotBlank(message = "Card no is required")
                                 String cardNo,
                                 @NotBlank(message = "CVC is required")
                                 String cvc,
                                 @NotNull(message = "Expiry date is required")
                                 String expiryDate,
                                 @NotNull(message = "Card type id required")
                                 @Min(value = 1, message = "Minimum card type id is 1")
                                 Long cardTypeId) {
}
