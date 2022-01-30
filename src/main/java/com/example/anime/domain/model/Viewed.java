package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveEpisodeId_UsersId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "viewed")
@IdClass(ClaveEpisodeId_UsersId.class)
public class Viewed {
    @Id
    public UUID episodeid;

    @Id
    public UUID usersid;



    public UUID getEpisodeid() {
        return episodeid;
    }

    public void setEpisodeid(UUID episodeid) {
        this.episodeid = episodeid;
    }

    public UUID getUsersid() {
        return usersid;
    }

    public void setUsersid(UUID usersid) {
        this.usersid = usersid;
    }
}
