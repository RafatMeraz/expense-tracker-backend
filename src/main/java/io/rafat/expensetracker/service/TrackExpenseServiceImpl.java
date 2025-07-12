package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.expense.AddExpenseRequest;
import io.rafat.expensetracker.dto.expense.AddExpenseResponse;
import io.rafat.expensetracker.dto.expense.ExpenseResponse;
import io.rafat.expensetracker.model.ETUserDetails;
import io.rafat.expensetracker.model.Expense;
import io.rafat.expensetracker.model.Users;
import io.rafat.expensetracker.repository.ExpenseRepository;
import io.rafat.expensetracker.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrackExpenseServiceImpl implements TrackExpenseService {
    private final ExpenseRepository expenseRepository;

    @Override
    public AddExpenseResponse addExpense(AddExpenseRequest addExpenseRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = ((ETUserDetails) authentication.getPrincipal()).user();
        Expense expense = Expense.builder()
                .user(user)
                .title(addExpenseRequest.title())
                .amount(addExpenseRequest.amount())
                .date(addExpenseRequest.date())
                .build();

        expense = expenseRepository.save(expense);

        return AddExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .date(expense.getDate())
                .build();
    }

    @Override
    public List<ExpenseResponse> getExpenses() {
        Users currentUser = UserUtils.getCurrentUser();

        List<Expense> expenseList = expenseRepository.findByUser(currentUser);

        return expenseList.stream()
                .map(expense -> ExpenseResponse.builder()
                        .id(expense.getId())
                        .title(expense.getTitle())
                        .date(expense.getDate())
                        .amount(expense.getAmount())
                        .build()).toList();
    }
}
