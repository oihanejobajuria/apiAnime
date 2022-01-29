package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID usersid;

    public String username;
    public String password;

    @ManyToMany(mappedBy = "favoritedby")
    Set<Anime> favorite;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mainUser", cascade = CascadeType.ALL)
    private Set<Users> usersIFollow = new HashSet<>();

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
}