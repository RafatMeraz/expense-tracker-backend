package io.rafat.expensetracker.controller;

import io.rafat.expensetracker.dto.expense.AddExpenseRequest;
import io.rafat.expensetracker.dto.expense.AddExpenseResponse;
import io.rafat.expensetracker.dto.expense.ExpenseResponse;
import io.rafat.expensetracker.service.TrackExpenseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
@AllArgsConstructor
public class TrackExpenseController {
    private final TrackExpenseService trackExpenseService;

    @PostMapping
    public ResponseEntity<?> addExpense(@Valid @RequestBody AddExpenseRequest addExpenseRequest) {
        AddExpenseResponse response = trackExpenseService.addExpense(addExpenseRequest);
        return new ResponseEntity<>(response,  HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllExpenses() {
        List<ExpenseResponse> expenseResponseList = trackExpenseService.getExpenses();
        return new ResponseEntity<>(expenseResponseList, HttpStatus.OK);
    }
}
