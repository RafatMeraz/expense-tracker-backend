package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.SuccessResponse;
import io.rafat.expensetracker.dto.expense.AddExpenseRequest;
import io.rafat.expensetracker.dto.expense.AddExpenseResponse;
import io.rafat.expensetracker.dto.expense.ExpenseResponse;
import io.rafat.expensetracker.dto.expense.UpdateExpenseRequest;

import java.util.List;

public interface TrackExpenseService {
    AddExpenseResponse addExpense(AddExpenseRequest addExpenseRequest);
    List<ExpenseResponse> getExpenses();
    SuccessResponse deleteExpense(Long id);
    SuccessResponse updateExpense(Long id, UpdateExpenseRequest updateExpenseRequest);
}
