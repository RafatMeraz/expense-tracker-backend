package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(String email, String password);
}
