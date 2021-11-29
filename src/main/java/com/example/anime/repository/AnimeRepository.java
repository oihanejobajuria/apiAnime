package com.example.anime.repository;

import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.projection.ProjectionAnimeWithoutDoblador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Anime, UUID> {
    List<ProjectionAnimeWithoutDoblador> findby();  // select      // nuevo docuemento
}
