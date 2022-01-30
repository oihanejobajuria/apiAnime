package com.example.anime.domain.model;

import com.example.anime.domain.model.Anime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID authorid;

    public String name;
    public String imageurl;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    public Set<Anime> animes;
}
