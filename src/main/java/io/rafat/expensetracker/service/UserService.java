package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.ChangePasswordRequest;
import io.rafat.expensetracker.dto.SuccessResponse;
import io.rafat.expensetracker.dto.UpdateProfileRequest;
import io.rafat.expensetracker.dto.UsersResponse;

public interface UserService {
    SuccessResponse changePassword(ChangePasswordRequest request);
    SuccessResponse updateProfile(UpdateProfileRequest request);
    UsersResponse getProfileData();
}
