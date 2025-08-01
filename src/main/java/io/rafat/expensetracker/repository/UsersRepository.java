package io.rafat.expensetracker.repository;

import io.rafat.expensetracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    boolean existsUsersByEmail(String email);
}
