package com.example.anime.repository;

import com.example.anime.domain.model.WatchlistAnime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WatchlistAnimeRepository extends JpaRepository<WatchlistAnime, UUID> {

}
