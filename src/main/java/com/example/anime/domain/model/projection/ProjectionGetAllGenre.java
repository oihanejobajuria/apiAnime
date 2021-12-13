package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGetAllGenre {
    UUID getGenreid();
    String getLabel();

//    @JsonIgnoreProperties("authors")
    Set<ProjectionGetAllGenre_Anime> getAnimes();

}
