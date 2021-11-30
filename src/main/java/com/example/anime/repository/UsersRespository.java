package com.example.anime.repository;

import com.example.anime.domain.dto.FileResult;
import com.example.anime.domain.dto.UsersResult;
import com.example.anime.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UsersRespository extends JpaRepository<Users, UUID> {
    Users findByUsername(String username);

    List<UsersResult> findBy();
}
