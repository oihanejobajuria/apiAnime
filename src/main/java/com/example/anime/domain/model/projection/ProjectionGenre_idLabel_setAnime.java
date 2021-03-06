package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"genreid", "label"})
public interface ProjectionGenre_idLabel_setAnime {
    UUID getGenreid();
    String getLabel();

    Set<ProjectionAnime_idName> getAnimes();

}
