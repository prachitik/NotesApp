package com.notes_app_with_jwt.NotesApp.controller;

import com.notes_app_with_jwt.NotesApp.dto.LoginRequest;
import com.notes_app_with_jwt.NotesApp.dto.LoginResponse;
import com.notes_app_with_jwt.NotesApp.dto.SignUpRequest;
import com.notes_app_with_jwt.NotesApp.security.JwtUtil;
import com.notes_app_with_jwt.NotesApp.service.AuthService;
import com.notes_app_with_jwt.NotesApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class MyNotesAppController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest request) {
        userService.signup(request);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        String token = jwtUtil.generateToken(username, role);
        // Create and return response
        LoginResponse response = new LoginResponse(token, username, role);
        return ResponseEntity.ok(response);
    }




}
