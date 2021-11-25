package com.example.anime.domain.dto;

import com.example.anime.domain.model.Users;

import java.util.List;
import java.util.UUID;

public class UsersResult {
    public UUID userid;
    public String username;

    public UsersResult(UUID userid, String username) {
        this.userid = userid;
        this.username = username;
    }

}

