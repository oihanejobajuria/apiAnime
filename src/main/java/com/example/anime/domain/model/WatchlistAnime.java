package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveWatchlistId_AnimeId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "watchlist_animes")
@IdClass(ClaveWatchlistId_AnimeId.class)
public class WatchlistAnime {
    @Id
    private UUID watchlistid;

    @Id
    private UUID animeid;

    @ManyToOne
    @JoinColumn(
            name="watchlistid", insertable = false, nullable = false, updatable = false)
    @JsonIgnoreProperties("listsAnimes")
    public Watchlist animeWithList;

    public UUID getWatchlistid() {
        return watchlistid;
    }

    public void setWatchlistid(UUID watchlistid) {
        this.watchlistid = watchlistid;
    }

    public UUID getAnimeid() {
        return animeid;
    }

    public void setAnimeid(UUID animeid) {
        this.animeid = animeid;
    }
}