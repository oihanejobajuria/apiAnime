package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAnime_idNameImg_setAuthor {
    UUID getAnimeid();
    String getName();
    String getImageurl();

    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor_idNameImg_setAnime> getAuthors();

//    @JsonIgnoreProperties("animes")
//    Set<ProjectionAnimeGenre> getGenre();
}
