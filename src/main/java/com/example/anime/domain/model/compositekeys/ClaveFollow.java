package com.example.anime.domain.model.compositekeys;

import java.io.Serializable;
import java.util.UUID;

public class ClaveFollow implements Serializable {
    public UUID userbase;
    public UUID followers_list;
}
