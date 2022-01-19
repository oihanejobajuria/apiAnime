package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveAnimeId_UsersId;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "favorites")
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

    public UUID getUserid() {
        return usersid;
    }

    public void setUserid(UUID usersid) {
        this.usersid = usersid;
    }
}
