package io.rafat.expensetracker.controller;

import io.rafat.expensetracker.dto.ChangePasswordRequest;
import io.rafat.expensetracker.dto.SuccessResponse;
import io.rafat.expensetracker.dto.UpdateProfileRequest;
import io.rafat.expensetracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        SuccessResponse response = userService.changePassword(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update-profile")
    public ResponseEntity<?> updateUser(@RequestBody UpdateProfileRequest updateProfileRequest) {
        SuccessResponse response = userService.updateProfile(updateProfileRequest);
        return ResponseEntity.ok(response);
    }
}
