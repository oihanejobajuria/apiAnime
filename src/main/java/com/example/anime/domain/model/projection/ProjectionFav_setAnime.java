package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public interface ProjectionFav_setAnime {
    @JsonIgnoreProperties("favoritedby")
    Set<ProjectionAnime_todo_setProj> getFavorite();
}
