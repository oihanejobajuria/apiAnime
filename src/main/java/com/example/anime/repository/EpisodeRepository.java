package com.example.anime.repository;

import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Episode;
import com.example.anime.domain.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EpisodeRepository extends JpaRepository<Episode, UUID> {}
