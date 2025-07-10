package io.rafat.expensetracker.repository;

import io.rafat.expensetracker.model.BankCardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardTypeRepository extends JpaRepository<BankCardType, Long> {
}
