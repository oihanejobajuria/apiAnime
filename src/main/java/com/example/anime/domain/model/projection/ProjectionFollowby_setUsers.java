package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public interface ProjectionFollowby_setUsers {
    Set<ProjectionUsers_idUsername> getFollowBy();
}
