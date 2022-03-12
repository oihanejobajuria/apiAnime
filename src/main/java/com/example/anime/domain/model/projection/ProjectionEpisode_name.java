package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "num"})
public interface ProjectionEpisode_name {
    String getName();
    int getNum();
    String getLink();
}
