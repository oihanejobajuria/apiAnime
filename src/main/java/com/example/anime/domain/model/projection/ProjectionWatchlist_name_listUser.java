package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"name", "userWithList"})
public interface ProjectionWatchlist_name_listUser {
    String getName();

    List<ProjectionUsers_username> getUserWithList();
    List<ProjectionAnime_idName> getAnimesInWatchlist();
}
