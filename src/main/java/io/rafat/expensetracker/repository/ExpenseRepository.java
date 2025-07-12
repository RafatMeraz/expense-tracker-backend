package io.rafat.expensetracker.repository;

import io.rafat.expensetracker.model.Expense;
import io.rafat.expensetracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(Users user);
}
