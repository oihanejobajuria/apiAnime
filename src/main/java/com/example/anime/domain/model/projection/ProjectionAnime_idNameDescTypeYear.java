package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.UUID;

@JsonPropertyOrder({"animeid", "name", "description", "type", "year"})
public interface ProjectionAnime_idNameDescTypeYear {
    UUID getAnimeid();
    String getName();
    String getDescription();
    String getType();
    int getYear();
}
