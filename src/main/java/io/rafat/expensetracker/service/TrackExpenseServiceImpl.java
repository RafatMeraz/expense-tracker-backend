package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.SuccessResponse;
import io.rafat.expensetracker.dto.expense.AddExpenseRequest;
import io.rafat.expensetracker.dto.expense.AddExpenseResponse;
import io.rafat.expensetracker.dto.expense.ExpenseResponse;
import io.rafat.expensetracker.dto.expense.UpdateExpenseRequest;
import io.rafat.expensetracker.model.ETUserDetails;
import io.rafat.expensetracker.model.Expense;
import io.rafat.expensetracker.model.Users;
import io.rafat.expensetracker.repository.ExpenseRepository;
import io.rafat.expensetracker.utils.UserUtils;
import io.rafat.expensetracker.utils.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public SuccessResponse deleteExpense(Long id) {
        Users user = UserUtils.getCurrentUser();
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isEmpty() || !user.equals(expense.get().getUser())) {
            throw new NotFoundException("Expense track not found");
        }

        expenseRepository.deleteById(id);

        return SuccessResponse.builder()
                .message("Expense track has been deleted")
                .build();
    }

    @Override
    public SuccessResponse updateExpense(Long id, UpdateExpenseRequest updateExpenseRequest) {
        Users user = UserUtils.getCurrentUser();
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isEmpty() || !user.equals(expense.get().getUser())) {
            throw new NotFoundException("Expense track not found");
        }

        if (!updateExpenseRequest.title().isEmpty()) {
            expense.get().setTitle(updateExpenseRequest.title());
        }
        if (updateExpenseRequest.amount() != null && updateExpenseRequest.amount().intValue() > 0) {
            expense.get().setAmount(updateExpenseRequest.amount());
        }
        if (updateExpenseRequest.date() != null) {
            expense.get().setDate(updateExpenseRequest.date());
        }

        expenseRepository.save(expense.get());

        return SuccessResponse.builder()
                .message("Expense has been updated")
                .build();
    }
}
