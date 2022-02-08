package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public interface ProjectionViewed_setEpisode {
    @JsonIgnoreProperties("viewedby")
    Set<ProjectionEpisode_idNameNum_listSeasonAnime> getViewed();
}
