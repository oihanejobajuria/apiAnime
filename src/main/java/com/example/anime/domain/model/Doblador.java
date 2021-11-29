package com.example.anime.domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;
import java.util.UUID;

public class Doblador {   // nuevo documento
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID dobladorid;

    public String name;
    public String imageurl;

    @ManyToMany(mappedBy = "dobladors")
    Set<Anime> animes;
}
