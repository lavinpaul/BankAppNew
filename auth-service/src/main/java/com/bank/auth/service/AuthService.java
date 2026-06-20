package com.bank.auth.service;

import com.bank.auth.dto.RegisterRequest;
import com.bank.auth.entity.Role;
import com.bank.auth.entity.User;
import com.bank.auth.repository.UserRepository;
import com.bank.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists";
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(String username, String password) {

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );

        if (!passwordEncoder.matches(
                password,
                user.getPassword())) {

            throw new RuntimeException("Invalid Credentials");
        }

        return jwtUtil.generateToken(username);
    }
}