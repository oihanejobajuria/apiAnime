package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "episode")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID episodeid;

    public String name;
    public int num;
    public String synopsis;

    @ManyToOne
    @JoinColumn(
            name="seasonid", nullable = false, updatable = false)
    @JsonIgnoreProperties("episodes")
    public Season seasonWithEpisodes;

}
