package com.example.anime.domain.dto;

import com.example.anime.domain.model.Users;

import java.util.List;
import java.util.UUID;

public class UsersResult {
    public UUID usersid;
    public String username;

    public UsersResult(UUID usersid, String username) {
        this.usersid = usersid;
        this.username = username;
    }

}

