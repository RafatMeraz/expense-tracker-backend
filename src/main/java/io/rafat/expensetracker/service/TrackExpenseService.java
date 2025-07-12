package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.expense.AddExpenseRequest;
import io.rafat.expensetracker.dto.expense.AddExpenseResponse;
import io.rafat.expensetracker.dto.expense.ExpenseResponse;

import java.util.List;

public interface TrackExpenseService {
    AddExpenseResponse addExpense(AddExpenseRequest addExpenseRequest);
    List<ExpenseResponse> getExpenses();
}
