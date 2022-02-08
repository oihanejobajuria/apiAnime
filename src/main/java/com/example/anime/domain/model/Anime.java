package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID animeid;

    public String name;
    public String description;
    public String type;
    public int year;
    public String imageurl;

    @ManyToMany
    @JoinTable(name = "anime_author",
            joinColumns = @JoinColumn(name = "animeid"),
            inverseJoinColumns = @JoinColumn(name = "authorid"))
    @JsonIgnoreProperties("animes")
    public Set<Author> authors;

    @ManyToMany
    @JoinTable(name = "anime_genre",
            joinColumns = @JoinColumn(name = "animeid"),
            inverseJoinColumns = @JoinColumn(name = "genreid"))
    @JsonIgnoreProperties("animes")
    public Set<Genre> genres;

    @ManyToMany
    @JoinTable(name = "favorite",
            joinColumns = @JoinColumn(name = "animeid"),
            inverseJoinColumns = @JoinColumn(name = "usersid"))
    public Set<Users> favoritedby;

    @ManyToMany
    @JoinTable(name = "watchlist_animes",
            joinColumns = @JoinColumn(name = "animeid"),
            inverseJoinColumns = @JoinColumn(name = "watchlistid"))
    @JsonIgnoreProperties("animesInWatchlist")
    public Set<Watchlist> watchlistedIn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animeWithSeasons")
    public List<Season> seasons;
}
