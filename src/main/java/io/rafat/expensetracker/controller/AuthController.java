package io.rafat.expensetracker.controller;

import io.rafat.expensetracker.dto.LoginRequest;
import io.rafat.expensetracker.dto.LoginResponse;
import io.rafat.expensetracker.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest.email(), loginRequest.password());
        return ResponseEntity.ok(response);
    }
}
