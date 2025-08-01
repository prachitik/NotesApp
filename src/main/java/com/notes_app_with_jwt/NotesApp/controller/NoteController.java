package com.notes_app_with_jwt.NotesApp.controller;

import com.notes_app_with_jwt.NotesApp.model.Note;
import com.notes_app_with_jwt.NotesApp.service.NoteService;
import com.notes_app_with_jwt.NotesApp.dto.NoteDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public Note createNote(@RequestBody NoteDTO noteDTO, Authentication authentication){
        return noteService.createNote(noteDTO, authentication.getName());
    }

    @GetMapping
    public List<Note> getUserNotes(Authentication authentication){
        System.out.println("Get notes for user: "+ authentication.getName());
        return noteService.getUserNotes(authentication.getName());
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable Long id, Authentication authentication){
        return noteService.getNoteById(id, authentication.getName());
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody NoteDTO noteDTO, Authentication authentication){
        return noteService.updateNote(id, noteDTO, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id, Authentication authentication) {
        noteService.deleteNote(id, authentication.getName());
    }

}
