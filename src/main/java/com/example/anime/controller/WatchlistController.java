package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestWatchlist;
import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.Watchlist;
import com.example.anime.domain.model.projection.ProjectionWatchlist_nameDesc_listUser;
import com.example.anime.domain.model.projection.ProjectionWatchlist_name_listUser;
import com.example.anime.repository.UsersRepository;
import com.example.anime.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/watchlists")
public class WatchlistController {

    @Autowired private UsersRepository usersRepository;
    @Autowired private WatchlistRepository watchlistRepository;

    @GetMapping("/")
    public ResponseEntity<?> todos(Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            return ResponseEntity.ok()
                    .body( watchlistRepository.findBy(ProjectionWatchlist_name_listUser.class) );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAnime(@PathVariable UUID id, Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            Watchlist comprobar = watchlistRepository.findById(id).orElse(null);
            if (comprobar == null)
                // error 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Error.message("No s'ha trobat la llista amb id " + id));
            else
                return ResponseEntity.ok().body(watchlistRepository.findByWatchlistid(comprobar.watchlistid, ProjectionWatchlist_nameDesc_listUser.class));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }


    @PostMapping("/")
    public ResponseEntity<?> addWatchlist(@RequestBody RequestWatchlist requestWatchlist, Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            Watchlist watchlist = watchlistRepository.findByName(requestWatchlist.name).orElse(null);

            if (watchlist!=null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body( Error.message("Aquesta watchlist ja existeix") );
            } else {
                Watchlist w = new Watchlist();
                w.name = requestWatchlist.name;
                w.description = requestWatchlist.description;
                w.userWithList = autorizado;
                watchlistRepository.save(w);

                return ResponseEntity.ok()
                        .body( watchlistRepository.findByWatchlistid(w.watchlistid, ProjectionWatchlist_nameDesc_listUser.class) );
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWatchlist(@PathVariable UUID id, Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            Watchlist watchlist = watchlistRepository.findById(id).orElse(null);

            if (watchlist==null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body( Error.message("Aquesta watchlist no existeix") );
            } else {
                Watchlist w = new Watchlist();
                w.watchlistid = id;
                w.name = watchlist.name;
                w.description = watchlist.description;
                w.userWithList = autorizado;
                watchlistRepository.delete(w);

                return ResponseEntity.ok()
                        .body( Error.message("Has eliminat correctament la watchlist " + watchlist.name) );
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }
}
