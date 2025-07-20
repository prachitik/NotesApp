package com.notes_app_with_jwt.NotesApp.model;

import jakarta.persistence.*;

@Entity
@Table(name="notesApp_user")
public class User{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userid;

    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Long getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}