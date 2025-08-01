package com.notes_app_with_jwt.NotesApp.service;

import com.notes_app_with_jwt.NotesApp.dto.NoteDTO;
import com.notes_app_with_jwt.NotesApp.model.Note;

import java.util.List;

public interface NoteService {
    Note createNote(NoteDTO noteDTO, String username);
    List<Note> getUserNotes(String username);
    Note getNoteById(Long id, String username);
    Note updateNote(Long id, NoteDTO noteDTO, String username);
    void deleteNote(Long id, String username);
}
