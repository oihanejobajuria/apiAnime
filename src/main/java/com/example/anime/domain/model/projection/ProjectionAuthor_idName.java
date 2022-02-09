package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"authorid", "name"})
public interface ProjectionAuthor_idName {
    UUID getAuthorid();
    String getName();
}
