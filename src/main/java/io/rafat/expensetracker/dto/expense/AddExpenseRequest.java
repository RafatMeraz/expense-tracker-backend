package io.rafat.expensetracker.dto.expense;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigInteger;
import java.util.Date;

@Builder(toBuilder = true)
public record AddExpenseRequest(
        @NotBlank(message = "Title is required") String title,
        @NotNull(message = "Amount is required")
        @Min(value = 0, message = "Minimum amount should be 0") BigInteger amount,
        @NotNull(message = "Date is required") Date date) {
}
