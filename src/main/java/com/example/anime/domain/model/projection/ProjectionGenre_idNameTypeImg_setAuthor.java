package com.example.anime.domain.model.projection;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGenre_idNameTypeImg_setAuthor {
    UUID getAnimeid();
    String getName();
    String getType();
    String getImageurl();

//    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor_idName> getAuthors();

//    @JsonIgnoreProperties("animes")
//    Set<ProjectionAnimeGenre> getGenre();
}
