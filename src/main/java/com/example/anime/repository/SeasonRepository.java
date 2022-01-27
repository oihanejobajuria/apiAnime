package com.example.anime.repository;

import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {
    //List<ProjectionAnimeWithoutDoblador> findby();  // version2
    // <T> List<T> findby(Class<T> type);
}
