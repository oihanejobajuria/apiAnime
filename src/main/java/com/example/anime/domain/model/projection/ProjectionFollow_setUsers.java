package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public interface ProjectionFollow_setUsers {
    Set<ProjectionUsers_idUsername> getFollow();
}
