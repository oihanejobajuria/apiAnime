package com.example.anime.domain.model.projection;


@JsonPropertyOrder({"name", "num"})
public interface ProjectionEpisode_name {
    String getName();
    int getNum();
    String getLink();
}
