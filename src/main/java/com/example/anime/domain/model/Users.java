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

    @ManyToMany
    @JoinTable(name = "followers",
            joinColumns = {
                    @JoinColumn(name = "userbase", referencedColumnName = "usersid", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "followers_list", referencedColumnName = "usersid", nullable = false)})
    @JsonIgnoreProperties("followBy")
    public Set<Users> follow;

    @ManyToMany(mappedBy = "follow")
    @JsonIgnoreProperties("follow")
    public Set<Users> followBy;


}