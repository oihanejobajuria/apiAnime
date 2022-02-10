package com.example.anime.domain.model.compositekeys;

import com.example.anime.domain.model.Viewed;

import java.io.Serializable;
import java.util.UUID;

public class ClaveEpisodeIdUsersId extends Viewed implements Serializable {
    public UUID episodeid;
    public UUID usersid;

    public ClaveEpisodeIdUsersId() {
    }

    public ClaveEpisodeIdUsersId(UUID episodeid, UUID usersid) {
        this.episodeid = episodeid;
        this.usersid = usersid;
    }
}
