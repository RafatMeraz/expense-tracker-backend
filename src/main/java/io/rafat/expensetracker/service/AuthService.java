package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.LoginResponse;
import io.rafat.expensetracker.dto.SignUpRequest;
import io.rafat.expensetracker.dto.SignUpResponse;

public interface AuthService {
    LoginResponse login(String email, String password);
    SignUpResponse signUp(SignUpRequest signUpRequest);
}
