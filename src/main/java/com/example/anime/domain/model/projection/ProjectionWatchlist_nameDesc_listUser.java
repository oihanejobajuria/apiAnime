package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"name", "description", "userWithList"})
public interface ProjectionWatchlist_nameDesc_listUser {
    String getName();
    String getDescription();

    List<ProjectionUsers_username> getUserWithList();
    List<ProjectionAnime_idName> getAnimesInWatchlist();
}
