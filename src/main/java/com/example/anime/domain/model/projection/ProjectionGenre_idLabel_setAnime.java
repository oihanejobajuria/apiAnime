package com.example.anime.domain.model.projection;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGenre_idLabel_setAnime {
    UUID getGenreid();
    String getLabel();

//    @JsonIgnoreProperties("animes")
    Set<ProjectionGenre_idNameTypeImg_setAuthor> getAnimes();

}
