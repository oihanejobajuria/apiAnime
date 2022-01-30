package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Watchlist;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/watchlist")  // este mapeado funciona con esto
public class WatchlistController {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @GetMapping("/")
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok().body(new ResponseList(watchlistRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnime(@PathVariable UUID id) {
        Watchlist comprobar = watchlistRepository.findById(id).orElse(null);

        if (comprobar == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat la llista amb id " + id));
        else
            return ResponseEntity.ok().body(comprobar);
    }


    @PostMapping("/")
    public ResponseEntity<?> createWatchlist(@RequestBody Watchlist watchlist) {
        for (Watchlist w : watchlistRepository.findAll()){
            if(w.name.equals(watchlist.name))
                // error 409
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Error.message("Ja existeix una watchlist amb el nom '" + watchlist.getName() + "'"));
        }
        watchlistRepository.save(watchlist);
        return ResponseEntity.ok().body(watchlist);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable UUID id){
        Watchlist watchlist = watchlistRepository.findById(id).orElse(null);

        if (watchlist==null){
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat la watchlist amd id '" + id  + "'"));
        }

        watchlistRepository.delete(watchlist);
        return ResponseEntity.ok().body(Error.message( "S'ha eliminat la watchlist " + watchlist.name + " amd id '" + id + "'"));

    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(){
        watchlistRepository.deleteAll();
        return ResponseEntity.ok().body( Error.message( "S'han eliminat totes les llistes" ) );
    }




}
