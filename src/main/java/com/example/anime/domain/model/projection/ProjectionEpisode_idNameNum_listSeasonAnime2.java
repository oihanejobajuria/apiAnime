package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"episodeid", "name", "num", "synopsis", "link"})
public interface ProjectionEpisode_idNameNum_listSeasonAnime2 {
    UUID getEpisodeid();
    String getName();
    int getNum();
    String getSynopsis();
    String getLink();

    List<ProjectionSeason_idNameNum_listAnime2> getSeasonWithEpisodes();
}
