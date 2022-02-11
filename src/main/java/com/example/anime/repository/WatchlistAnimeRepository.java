package com.example.anime.repository;

import com.example.anime.domain.model.WatchlistAnime;
import com.example.anime.domain.model.compositekeys.ClaveWatchlistIdAnimeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WatchlistAnimeRepository extends JpaRepository<WatchlistAnime, ClaveWatchlistIdAnimeId> {
    <T> T findByWatchlistid(UUID id);
    <T> T findByWatchlistid(UUID id, Class<T> type);
}
