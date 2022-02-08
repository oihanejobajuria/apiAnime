package com.example.anime.repository;

import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {
    <T> T findBySeasonid(UUID seasonid, Class<T> type);
    <T> List<T> findBy(Class<T> type);
}
