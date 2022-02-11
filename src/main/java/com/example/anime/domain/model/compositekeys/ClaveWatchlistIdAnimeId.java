package com.example.anime.domain.model.compositekeys;

import com.example.anime.domain.model.WatchlistAnime;

import java.io.Serializable;
import java.util.UUID;

public class ClaveWatchlistIdAnimeId extends WatchlistAnime implements Serializable {
    public UUID watchlistid;
    public UUID animeid;

    public ClaveWatchlistIdAnimeId() {
    }

    public ClaveWatchlistIdAnimeId(UUID animeid, UUID watchlistid) {
    }
}
