package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"animeid", "name", "authors", "genres"})
public interface ProjectionAnime_idName_setGenre {
    UUID getAnimeid();
    String getName();

    @JsonIgnoreProperties("animes")
    Set<ProjectionGenre_label> getGenres();

}
