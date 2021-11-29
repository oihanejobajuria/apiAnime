package com.example.anime.repository;

import com.example.anime.domain.model.Doblador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DobladorRepository extends JpaRepository<Doblador, UUID> {         //nuevo docuemento
}
