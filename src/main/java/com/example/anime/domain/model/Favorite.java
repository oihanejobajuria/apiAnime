package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "anime")
public class Favorite {
    @Id
    public UUID animeid;

    @Id
    public UUID userid;



    public UUID getAnimeid() {
        return animeid;
    }

    public void setAnimeid(UUID animeid) {
        this.animeid = animeid;
    }

    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }
}
