package io.rafat.expensetracker.dto.expense;

import lombok.Builder;

import java.math.BigInteger;
import java.util.Date;

@Builder(toBuilder = true)
public record ExpenseResponse(Long id, String title, BigInteger amount, Date date) {
}
