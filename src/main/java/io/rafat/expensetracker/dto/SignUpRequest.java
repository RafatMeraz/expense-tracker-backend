package io.rafat.expensetracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record SignUpRequest(@Email(message = "Enter a valid email") String email,
                            @NotBlank(message = "Password is required")
                            @Size(min = 7, message = "Password should more than 6 letters") String password,
                            @NotBlank(message = "Full name is required") String fullName,
                            MultipartFile avatar) {

}
