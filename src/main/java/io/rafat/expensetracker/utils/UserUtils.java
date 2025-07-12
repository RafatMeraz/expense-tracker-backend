package io.rafat.expensetracker.utils;

import io.rafat.expensetracker.model.ETUserDetails;
import io.rafat.expensetracker.model.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((ETUserDetails) authentication.getPrincipal()).user();
    }
}
