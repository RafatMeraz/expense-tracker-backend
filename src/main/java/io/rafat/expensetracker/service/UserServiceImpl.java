package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.ChangePasswordRequest;
import io.rafat.expensetracker.dto.SuccessResponse;
import io.rafat.expensetracker.dto.UpdateProfileRequest;
import io.rafat.expensetracker.model.Users;
import io.rafat.expensetracker.repository.UsersRepository;
import io.rafat.expensetracker.utils.UserUtils;
import io.rafat.expensetracker.utils.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SuccessResponse changePassword(ChangePasswordRequest request) {
        Users currentUser = UserUtils.getCurrentUser();

        if (!passwordEncoder.matches(request.currentPassword(), currentUser.getPassword())) {
            throw new BadRequestException("Current password is incorrect");
        }

        currentUser.setPassword(passwordEncoder.encode(request.newPassword()));

        usersRepository.save(currentUser);

        return new SuccessResponse("Successfully changed password");
    }

    @Override
    public SuccessResponse updateProfile(UpdateProfileRequest request) {
        Users currentUser = UserUtils.getCurrentUser();

        if (StringUtils.hasText(request.fullName())) {
            currentUser.setFullName(request.fullName());
        }

        usersRepository.save(currentUser);

        return new SuccessResponse("Successfully updated profile");
    }
}
