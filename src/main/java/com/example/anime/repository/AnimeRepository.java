package com.example.anime.repository;

import com.example.anime.domain.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Anime, UUID> {
    //List<ProjectionAnimeWithoutDoblador> findby();  // version2
    // <T> List<T> findby(Class<T> type);
}
