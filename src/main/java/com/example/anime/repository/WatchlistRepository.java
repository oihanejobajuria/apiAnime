package com.example.anime.repository;

import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WatchlistRepository extends JpaRepository<Watchlist, UUID> {

    Optional<Watchlist> findByName(String name);
}
