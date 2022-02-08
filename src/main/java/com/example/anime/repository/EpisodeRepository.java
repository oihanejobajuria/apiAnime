package com.example.anime.repository;

import com.example.anime.domain.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EpisodeRepository extends JpaRepository<Episode, UUID> {
    <T> T findByEpisodeid(UUID episodeid, Class<T> type);
    <T> List<T> findBy(Class<T> type);
}
