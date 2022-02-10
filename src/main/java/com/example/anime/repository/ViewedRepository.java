package com.example.anime.repository;

import com.example.anime.domain.model.Viewed;
import com.example.anime.domain.model.compositekeys.ClaveEpisodeIdUsersId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ViewedRepository extends JpaRepository<Viewed, ClaveEpisodeIdUsersId> {
}
