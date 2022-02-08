package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"episodeid", "name"})
public interface ProjectionEpisode_idNameNum {
    UUID getEpisodeid();
    String getName();
    String getNum();
}
