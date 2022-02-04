package com.example.anime.repository;

import com.example.anime.domain.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Anime, UUID> {

     <T> List<T> findBy(Class<T> type);

     <T> T findByAnimeid(UUID id, Class<T> type);

}
