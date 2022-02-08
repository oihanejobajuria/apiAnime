package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"episodeid", "name", "num"})
public interface ProjectionEpisode_idNameNum_listSeasonAnime2 {
    UUID getEpisodeid();
    String getName();
    int getNum();

    List<ProjectionSeason_idNameNum_listAnime2> getSeasonWithEpisodes();
}
