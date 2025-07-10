package io.rafat.expensetracker.service;

import io.rafat.expensetracker.model.ETUserDetails;
import io.rafat.expensetracker.model.Users;
import io.rafat.expensetracker.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ETUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findByEmail(email);

        if (user.isPresent()) {
            return new ETUserDetails(user.get());
        }

        throw new UsernameNotFoundException(email);
    }
}
