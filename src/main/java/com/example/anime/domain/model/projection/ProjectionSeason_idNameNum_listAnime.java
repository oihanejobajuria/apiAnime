package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"seasonid", "name", "num"})
public interface ProjectionSeason_idNameNum_listAnime {
    UUID getSeasonid();
    String getName();
    int getNum();

    List<ProjectionAnime_idName> getAnimeWithSeasons();
}
