package io.rafat.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder(toBuilder = true)
public record ChangePasswordRequest(
        @NotBlank(message = "Current password is required")
        String currentPassword,
        @NotBlank(message = "New password is required")
        @Size(min = 7, message = "Minimum length of new password should be 7 letters")
        String newPassword) {
}
