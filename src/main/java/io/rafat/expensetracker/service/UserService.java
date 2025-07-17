package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.ChangePasswordRequest;
import io.rafat.expensetracker.dto.SuccessResponse;
import io.rafat.expensetracker.dto.UpdateProfileRequest;

public interface UserService {
    SuccessResponse changePassword(ChangePasswordRequest request);
    SuccessResponse updateProfile(UpdateProfileRequest request);
}
