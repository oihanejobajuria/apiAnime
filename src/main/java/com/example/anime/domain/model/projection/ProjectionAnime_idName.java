package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"animeid"})
public interface ProjectionAnime_idName {
    UUID getAnimeid();
    String getName();
}
