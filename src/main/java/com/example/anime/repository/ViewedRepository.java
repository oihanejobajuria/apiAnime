package com.example.anime.repository;

import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.Viewed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViewedRepository extends JpaRepository<Viewed, UUID> {         //nuevo docuemento
//    <T> List<T> findBy(Class<T> type);
}
