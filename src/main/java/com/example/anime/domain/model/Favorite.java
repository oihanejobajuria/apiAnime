package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveAnimeId_UsersId;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "favorite")
@IdClass(ClaveAnimeId_UsersId.class)
public class Favorite {
    @Id
    public UUID animeid;

    @Id
    public UUID usersid;



    public UUID getAnimeid() {
        return animeid;
    }

    public void setAnimeid(UUID animeid) {
        this.animeid = animeid;
    }

    public UUID getUsersid() {
        return usersid;
    }

    public void setUsersid(UUID usersid) {
        this.usersid = usersid;
    }
}
