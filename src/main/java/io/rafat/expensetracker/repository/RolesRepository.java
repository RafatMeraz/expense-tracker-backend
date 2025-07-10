package io.rafat.expensetracker.repository;

import io.rafat.expensetracker.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
}
