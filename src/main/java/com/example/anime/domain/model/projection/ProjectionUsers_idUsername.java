package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"usersid"})
public interface ProjectionUsers_idUsername {
    UUID getUsersid();
    String getUsername();
}
