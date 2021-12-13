package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGetAllAuthor {
    UUID getAuthorid();
    String getName();
    String getImageurl();

    @JsonIgnoreProperties("authors")
    Set<ProjectionGetAllAuthor_Anime> getAnimes();

}
