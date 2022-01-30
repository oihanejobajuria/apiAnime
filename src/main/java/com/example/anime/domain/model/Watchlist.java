package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveAnimeId_UsersId;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "watchlist")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID watchlistid;

    public String name;
    public String description;

    public UUID getWatchlistid() {
        return watchlistid;
    }
    public void setWatchlistid(UUID watchlistid) {
        this.watchlistid = watchlistid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }}