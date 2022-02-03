    package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestWatchlist;
import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.Watchlist;
import com.example.anime.domain.model.WatchlistAnime;
import com.example.anime.repository.UsersRepository;
import com.example.anime.repository.WatchlistAnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/watchlist_animes")
public class WatchlistAnimeController {

    @Autowired private UsersRepository usersRepository;
    @Autowired private WatchlistAnimeRepository watchlistAnimeRepository;


    @GetMapping("/")
    public ResponseEntity<?> todos(Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());
            return ResponseEntity.ok()
                    .body( watchlistAnimeRepository.findAll() );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWatchlistAnime(@PathVariable UUID id, Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            WatchlistAnime comprobar = watchlistAnimeRepository.findById(id).orElse(null);
            if (comprobar == null)
                // error 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Error.message("No s'ha trobat la llista amb id " + id));
            else
                return ResponseEntity.ok().body(comprobar);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }

//
//    @PostMapping("/")
//    public ResponseEntity<?> addWatchlistAnime(@RequestBody RequestWatchlist requestWatchlist, Authentication authentication) {
//        if (authentication.getName() != null) {
//            Users autorizado = usersRepository.findByUsername(authentication.getName());
//
//            Watchlist watchlist = watchlistAnimeRepository.findByName(requestWatchlist.name).orElse(null);
//
//            if (watchlist!=null) {
//                return ResponseEntity.status(HttpStatus.CONFLICT).body( Error.message("Aquesta watchlist ja existeix") );
//            } else {
//                WatchlistAnime w = new Watchlist();
//                w.animeWithList = requestWatchlist
//                watchlistAnimeRepository.save(w);
//
//                return ResponseEntity.ok()
//                        .body( w );
//            }
//
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteWatchlist(@PathVariable UUID id, Authentication authentication) {
//        if (authentication.getName() != null) {
//            Users autorizado = usersRepository.findByUsername(authentication.getName());
//
//            Watchlist watchlist = watchlistAnimeRepository.findById(id).orElse(null);
//
//            if (watchlist==null) {
//                return ResponseEntity.status(HttpStatus.CONFLICT).body( Error.message("Aquesta watchlist no existeix") );
//            } else {
//                Watchlist w = new Watchlist();
//                w.watchlistid = id;
//                w.name = watchlist.name;
//                w.description = watchlist.description;
//                w.userWithList = autorizado;
//                watchlistAnimeRepository.delete(w);
//
//                return ResponseEntity.ok()
//                        .body( Error.message("Has eliminat correctament la watchlist " + watchlist.name) );
//            }
//
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
//    }
}
