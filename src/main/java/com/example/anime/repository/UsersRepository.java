package com.example.anime.repository;

import com.example.anime.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.UUID;

@EnableJpaRepositories
public interface UsersRepository extends JpaRepository<Users, UUID> {
    Users findByUsername(String name);

    <T> List<T> findByUsername(String username, Class<T> type);

    <T> List<T> findBy(Class<T> type);
}
