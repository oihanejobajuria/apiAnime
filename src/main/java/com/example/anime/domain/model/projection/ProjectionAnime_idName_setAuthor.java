package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"animeid", "name"})
public interface ProjectionAnime_idName_setAuthor {
    UUID getAnimeid();
    String getName();

    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor_idName_setAnime> getAuthors();
}
