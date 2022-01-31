package com.example.anime.repository;

import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.UsersFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.UUID;

@EnableJpaRepositories
public interface FollowRepository extends JpaRepository<UsersFollow, UUID> {
}
