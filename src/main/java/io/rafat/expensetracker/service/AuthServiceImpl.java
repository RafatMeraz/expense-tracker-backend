package io.rafat.expensetracker.service;

import io.rafat.expensetracker.config.JwtTokenProvider;
import io.rafat.expensetracker.dto.*;
import io.rafat.expensetracker.model.ETUserDetails;
import io.rafat.expensetracker.model.Roles;
import io.rafat.expensetracker.model.Users;
import io.rafat.expensetracker.repository.RolesRepository;
import io.rafat.expensetracker.repository.UsersRepository;
import io.rafat.expensetracker.utils.exception.AlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;

    @Override
    public LoginResponse login(String email, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtToken accessToken = jwtTokenProvider.generateToken(authentication.getName());
        JwtToken refreshToken = jwtTokenProvider.generateRefreshToken(authentication.getName());

        Users user = ((ETUserDetails)authentication.getPrincipal()).user();

        return LoginResponse.builder()
                .user(UsersResponse.getUsersResponse(user))
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        boolean isEmailExists = usersRepository.existsUsersByEmail(signUpRequest.email());
        if (isEmailExists) {
            throw new AlreadyExistsException("Email already exists");
        }

        Roles role = rolesRepository.findByName("ROLE_USER");

        Users user = Users.builder()
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .fullName(signUpRequest.fullName())
                .roles(Collections.singleton(role))
                .build();

        user = usersRepository.save(user);

        JwtToken accessToken = jwtTokenProvider.generateToken(user.getEmail());
        JwtToken refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        return SignUpResponse.builder()
                .user(UsersResponse.getUsersResponse(user))
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
