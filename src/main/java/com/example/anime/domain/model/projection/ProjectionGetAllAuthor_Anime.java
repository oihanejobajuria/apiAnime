package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGetAllAuthor_Anime {
    UUID getAnimeid();
    String getName();
    String getImageurl();

    @JsonIgnoreProperties("animes")
    Set<ProjectionGetAllAuthor> getAuthors();

//    @JsonIgnoreProperties("animes")
//    Set<ProjectionAnimeGenre> getGenre();
}
