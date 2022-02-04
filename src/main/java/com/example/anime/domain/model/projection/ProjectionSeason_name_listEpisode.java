package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"name"})
public interface ProjectionSeason_name_listEpisode {
    String getName();

    List<ProjectionEpisode_name> getEpisodes();
}
