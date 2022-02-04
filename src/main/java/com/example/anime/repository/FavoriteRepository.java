package com.example.anime.repository;

import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.projection.ProjectionGenre_idLabel_setAnime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    <T> List<T> findBy(Class<T> type);
}
