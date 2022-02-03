package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveWatchlistIdAnimeId;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "watchlist_animes")
@IdClass(ClaveWatchlistIdAnimeId.class)
public class WatchlistAnime {
    @Id
    public UUID watchlistid;

    @Id
    public UUID animeid;

}

























//    Special thanks to Dani
