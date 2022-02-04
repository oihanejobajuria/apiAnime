package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"animeid", "name", "description", "type", "year", "imageurl", "authors", "genres"})
public interface ProjectionAnime_todo_setProj {
    UUID getAnimeid();
    String getName();
    String getDescription();
    String getType();
    int getYear();
    String getImageurl();

    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor_name> getAuthors();

    @JsonIgnoreProperties("animes")
    Set<ProjectionGenre_label> getGenres();

    @JsonIgnoreProperties("animes")
    List<ProjectionSeason_name_listEpisode> getSeasons();

}
