package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGetAllGenre_Anime_Author {
    UUID getAuthorid();
    String getName();
}
