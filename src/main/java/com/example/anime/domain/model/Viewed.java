package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveEpisodeIdUsersId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "viewed")
@IdClass(ClaveEpisodeIdUsersId.class)
public class Viewed {
    @Id
    public UUID episodeid;

    @Id
    public UUID usersid;



}
