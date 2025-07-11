package io.rafat.expensetracker.controller;

import io.rafat.expensetracker.dto.LoginRequest;
import io.rafat.expensetracker.dto.LoginResponse;
import io.rafat.expensetracker.dto.SignUpRequest;
import io.rafat.expensetracker.dto.SignUpResponse;
import io.rafat.expensetracker.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        SignUpResponse response = authService.signUp(signUpRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
