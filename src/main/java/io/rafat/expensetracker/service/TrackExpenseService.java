package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.expense.AddExpenseRequest;
import io.rafat.expensetracker.dto.expense.AddExpenseResponse;

public interface TrackExpenseService {
    AddExpenseResponse addExpense(AddExpenseRequest addExpenseRequest);
}
