package com.notes_app_with_jwt.NotesApp.service;

import com.notes_app_with_jwt.NotesApp.dto.LoginRequest;
import com.notes_app_with_jwt.NotesApp.model.User;
import com.notes_app_with_jwt.NotesApp.repository.UserRepository;
import com.notes_app_with_jwt.NotesApp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(LoginRequest request) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Load user and generate token
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
    }
}
