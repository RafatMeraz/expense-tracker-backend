package io.rafat.expensetracker.repository;

import io.rafat.expensetracker.model.BankCard;
import io.rafat.expensetracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {
    List<BankCard> findAllByUser(Users user);
    Optional<BankCard> findAllByNumber(String cardNo);
}
