package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestWatchlistAnime;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.Watchlist;
import com.example.anime.domain.model.WatchlistAnime;
import com.example.anime.domain.model.compositekeys.ClaveWatchlistIdAnimeId;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.UsersRepository;
import com.example.anime.repository.WatchlistAnimeRepository;
import com.example.anime.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/watchlist_animes")
public class WatchlistAnimeController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private WatchlistAnimeRepository watchlistAnimeRepository;
    @Autowired
    private WatchlistRepository watchlistRepository;
    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public ResponseEntity<?> todos(Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());
            return ResponseEntity.ok()
                    .body(new ResponseList(watchlistAnimeRepository.findAll()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("No estas autoritzat"));
    }



//    Es recurrente buscar por ID cuando, en WatchlistController, ya sacamos los mismos datos con la mima búsqueda.


    @PostMapping("/")
    public ResponseEntity<?> addWatchlistAnime(@RequestBody RequestWatchlistAnime requestWatchlistAnime, Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());
            Watchlist watchlist = watchlistRepository.findById(requestWatchlistAnime.watchlistid).orElse(null);
            Anime anime = animeRepository.findById(requestWatchlistAnime.animeid).orElse(null);

            if (autorizado.usersid != watchlist.userWithList.usersid){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("No puedes editar watchlists que no son tuyas"));
            }

            if (watchlist == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Error.message("Aquesta watchlist no existeix"));

            } else if (anime == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Error.message("Aquest anime no existeix"));

            } else {
                Anime isInWatchlist = new Anime();
                for (Anime a : watchlist.animesInWatchlist)
                    if (a.animeid == requestWatchlistAnime.animeid) isInWatchlist = a;

                if (isInWatchlist.animeid == requestWatchlistAnime.animeid) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(Error.message("Ja existeix el anime a la watchlist"));

                } else {
                    WatchlistAnime wAnime = new WatchlistAnime();
                    wAnime.animeid = requestWatchlistAnime.animeid;
                    wAnime.watchlistid = watchlist.watchlistid;
                    watchlistAnimeRepository.save(wAnime);

                    return ResponseEntity.ok()
                            .body(wAnime);
                }
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("No estas autoritzat"));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteWatchlist(@RequestBody RequestWatchlistAnime requestWatchlistAnime, Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());
            Watchlist watchlist = watchlistRepository.findById(requestWatchlistAnime.watchlistid).orElse(null);
            Anime anime = animeRepository.findById(requestWatchlistAnime.animeid).orElse(null);

            if (autorizado.usersid != watchlist.userWithList.usersid){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("No puedes editar watchlists que no son tuyas"));
            }

            if (watchlist == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Error.message("Aquesta watchlist no existeix"));
            }

            if (anime == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Error.message("Aquest anime no existeix"));

            } else {
                for (Anime a : watchlist.animesInWatchlist)
                    if (a.animeid != requestWatchlistAnime.animeid) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(Error.message("No existeix el anime a la watchlist"));
                    } else {
//                            WatchlistAnime wAnime = new WatchlistAnime();
//                            wAnime.animeid = requestWatchlistAnime.animeid;
//                            wAnime.watchlistid = watchlist.watchlistid;
                        watchlistAnimeRepository.delete(new ClaveWatchlistIdAnimeId(requestWatchlistAnime.animeid, watchlist.watchlistid ));

                        return ResponseEntity.ok()
                                .body(Error.message("S'ha eliminat l'anime " + a.name + " de la watchlist " + watchlist.name));
                    }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("No estas autoritzat"));
    }
}
