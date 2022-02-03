package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "watchlist")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID watchlistid;

    public String name;
    public String description;

    @ManyToOne
    @JoinColumn(
            name="usersid", nullable = false, updatable = false)
    public Users userWithList;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animesInWatchlist")
//    public List<Anime> listsAnimes;

    @ManyToMany(mappedBy = "watchlistedIn")
    @JsonIgnoreProperties("watchlistedIn")
    public Set<Anime> animesInWatchlist;
}