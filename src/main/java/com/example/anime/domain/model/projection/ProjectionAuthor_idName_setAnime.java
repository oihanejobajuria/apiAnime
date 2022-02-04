package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"authorid", "name"})
public interface ProjectionAuthor_idName_setAnime {
    UUID getAuthorid();
    String getName();

    @JsonIgnoreProperties("authors")
    Set<ProjectionAnime_idName_setAuthor> getAnimes();

}
