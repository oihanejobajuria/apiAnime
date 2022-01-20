package com.example.anime.domain.model;

import javax.persistence.*;
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