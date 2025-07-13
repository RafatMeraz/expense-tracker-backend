package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.ChangePasswordRequest;
import io.rafat.expensetracker.dto.SuccessResponse;

public interface UserService {
    SuccessResponse changePassword(ChangePasswordRequest request);
}
