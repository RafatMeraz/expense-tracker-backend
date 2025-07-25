package io.rafat.expensetracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@Email(message = "Email is not valid") String email,
                           @NotBlank(message = "Password is required") String password) {
}
