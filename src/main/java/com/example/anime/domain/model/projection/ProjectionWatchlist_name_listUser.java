package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"name", "userWithList"})
public interface ProjectionWatchlist_name_listUser {
    String getWatchlistid();
    String getName();

    List<ProjectionUsers_username> getUserWithList();
    List<ProjectionAnime_idName> getAnimesInWatchlist();
}
