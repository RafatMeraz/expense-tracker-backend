package io.rafat.expensetracker.repository;

import io.rafat.expensetracker.model.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {
}
