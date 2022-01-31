package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveFollow;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "followers")
@IdClass(ClaveFollow.class)
public class UsersFollow {
    @Id
    public UUID userbase;

    @Id
    public UUID followers_list;
}
