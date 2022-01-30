package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "watchlist")
public class WatchlistAnime {
    @Id
    public UUID watchlistid;
    @Id
    public UUID animeid;


    @ManyToOne
    @JoinColumn(
            name="watchlistid", nullable = false, updatable = false)
    @JsonIgnoreProperties("listsAnimes")
    public Watchlist animeWithList;
}