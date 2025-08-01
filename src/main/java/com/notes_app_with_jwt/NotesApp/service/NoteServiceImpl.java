package com.notes_app_with_jwt.NotesApp.service;

import com.notes_app_with_jwt.NotesApp.dto.NoteDTO;
import com.notes_app_with_jwt.NotesApp.model.Note;
import com.notes_app_with_jwt.NotesApp.model.User;
import com.notes_app_with_jwt.NotesApp.repository.NoteRepository;
import com.notes_app_with_jwt.NotesApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Note createNote(NoteDTO noteDTO, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Note note = new Note(noteDTO.getTitle(), noteDTO.getContent(), LocalDateTime.now(), LocalDateTime.now(), user);
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getUserNotes(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return noteRepository.findByUser(user);
    }

    @Override
    public Note getNoteById(Long id, String username) {
        Note note = noteRepository.findById(id).orElseThrow();
        if(! note.getUser().getUsername().equals(username)) throw new SecurityException("Unauthorized Access");
        return note;
    }

    @Override
    public Note updateNote(Long id, NoteDTO noteDTO, String username) {
        Note note = getNoteById(id, username);
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setUpdatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id, String username) {
        Note note = getNoteById(id, username);
        noteRepository.delete(note);
    }
}
