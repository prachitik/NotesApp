package com.notes_app_with_jwt.NotesApp.repository;

import com.notes_app_with_jwt.NotesApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
