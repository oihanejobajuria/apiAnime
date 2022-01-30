package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

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

    @ManyToMany(mappedBy = "viewedby")
    Set<Episode> viewed;

//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    private Users userbase;

//    @OneToMany(mappedBy="userbase")
//    private List<Users> followerList = new ArrayList<Users>();

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