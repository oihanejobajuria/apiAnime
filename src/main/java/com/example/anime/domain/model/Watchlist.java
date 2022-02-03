package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveAnimeId_UsersId;
import com.example.anime.domain.model.compositekeys.ClaveWatchlistId_AnimeId;
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
    @JsonIgnoreProperties("watchlists")
    public Users userWithList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animeWithList")
    public List<WatchlistAnime> listsAnimes;


}