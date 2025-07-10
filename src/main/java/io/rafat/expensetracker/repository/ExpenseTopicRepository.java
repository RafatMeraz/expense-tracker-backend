package io.rafat.expensetracker.repository;

import io.rafat.expensetracker.model.ExpenseTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseTopicRepository extends JpaRepository<ExpenseTopic, Long> {
}
