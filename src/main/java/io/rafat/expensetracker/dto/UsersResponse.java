package io.rafat.expensetracker.dto;

import io.rafat.expensetracker.model.Users;
import lombok.Builder;

@Builder(toBuilder = true)
public record UsersResponse(Long id, String email, String fullName, String avatarUrl) {

    public static UsersResponse getUsersResponse(Users user) {
        return UsersResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }
}
