package com.example.anime.repository;

import com.example.anime.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    <T> List<T> findBy(Class<T> type);
    <T> T findByAuthorid(UUID authorid, Class<T> type);

}
