package com.example.anime.repository;

import com.example.anime.domain.model.Genre;
import com.example.anime.domain.model.projection.ProjectionGenre_idLabel_setAnime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {

    List<ProjectionGenre_idLabel_setAnime> findBy();

    <T> T findByGenreid(UUID genreid, Class<T> type);

    <T> List<T> findBy(Class<T> type);

}
