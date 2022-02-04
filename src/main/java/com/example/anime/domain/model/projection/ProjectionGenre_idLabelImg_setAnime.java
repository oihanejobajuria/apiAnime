package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"genreid", "label", "imageurl"})
public interface ProjectionGenre_idLabelImg_setAnime {
    UUID getGenreid();
    String getLabel();
    String getImageurl();

    Set<ProjectionAnime_idName> getAnimes();
}
