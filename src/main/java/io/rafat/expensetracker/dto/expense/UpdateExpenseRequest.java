package io.rafat.expensetracker.dto.expense;

import java.math.BigInteger;
import java.util.Date;

public record UpdateExpenseRequest(String title, BigInteger amount, Date date) {
}
