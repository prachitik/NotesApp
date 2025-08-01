package com.notes_app_with_jwt.NotesApp.repository;

import com.notes_app_with_jwt.NotesApp.model.Note;
import com.notes_app_with_jwt.NotesApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
}
