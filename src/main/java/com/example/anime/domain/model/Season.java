package com.example.anime.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "season")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID seasonid;

    public String name;
    public int num;

    @ManyToOne
    @JoinColumn(
            name="animeid", nullable = false, updatable = false)
    @JsonIgnoreProperties("seasons")
    public Anime animeWithSeasons;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seasonWithEpisodes")
    public List<Episode> episodes;

}
