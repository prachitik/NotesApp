package com.notes_app_with_jwt.NotesApp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // your JWT validation logic here
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(token);
            } catch (Exception e) {
                // Log the error or return 401/403
            }
        }

        System.out.println("JWT Filter called");
        System.out.println("Auth Header: " + authHeader);
        System.out.println("Username: " + username);
        System.out.println("Authenticated? " + (SecurityContextHolder.getContext().getAuthentication() != null));


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("Username from token: " + username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("Authorities: " + userDetails.getAuthorities());
            System.out.println("Auth token set: " + SecurityContextHolder.getContext().getAuthentication());
            if (jwtUtil.validateToken(token)) {
                System.out.println("Token is valid.");
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else{
                System.out.println("Token is invalid.");
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.equals("/notes/login") || path.equals("/notes/signup");
    }
}
