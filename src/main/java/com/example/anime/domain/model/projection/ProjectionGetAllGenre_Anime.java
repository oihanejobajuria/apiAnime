package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGetAllGenre_Anime {
    UUID getAnimeid();
    String getName();
    String getType();
    String getImageurl();

//    @JsonIgnoreProperties("animes")
    Set<ProjectionGetAllGenre_Anime_Author> getAuthors();

//    @JsonIgnoreProperties("animes")
//    Set<ProjectionAnimeGenre> getGenre();
}
