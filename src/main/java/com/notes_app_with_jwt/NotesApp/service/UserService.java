package com.notes_app_with_jwt.NotesApp.service;

import com.notes_app_with_jwt.NotesApp.dto.SignUpRequest;
import com.notes_app_with_jwt.NotesApp.model.Role;
import com.notes_app_with_jwt.NotesApp.model.User;
import com.notes_app_with_jwt.NotesApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signup(SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        userRepository.save(user);
    }
}
