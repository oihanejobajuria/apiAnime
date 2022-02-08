package com.example.anime.controller;


import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.*;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.projection.ProjectionFav_setAnime;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.FavoriteRepository;
import com.example.anime.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/favorites")
public class FavoritesController {

    @Autowired private UsersRepository usersRepository;
    @Autowired private FavoriteRepository favoriteRepository;
    @Autowired private AnimeRepository animeRepository;


    @GetMapping("/")
    public ResponseEntity<?> todosFav(Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            return ResponseEntity.ok()
                    .body( usersRepository.findByUsername(authentication.getName(), ProjectionFav_setAnime.class) );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }


    @PostMapping("/")
    public ResponseEntity<?> addFav(@RequestBody RequestFavorite requestFavorite, Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            boolean estaAnime=false, estaFavs=false;
            for (Anime a : animeRepository.findAll()){
                if(a.animeid.equals(requestFavorite.animeid)) {
                    estaAnime = true;
                }
            }
            for (Favorite f : favoriteRepository.findAll()) {
                if (f.animeid.equals(requestFavorite.animeid)){
                    estaFavs = true;
                }
            }

            if(!estaAnime){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body( Error.message("Aquesta id no perteneix a cap anime existent") );
            } else {
                if (estaFavs) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body( Error.message("Aquest anime ja esta en favorits") );
                } else {
                    Favorite f = new Favorite();
                    f.animeid = requestFavorite.animeid;
                    f.usersid = autorizado.usersid;
                    favoriteRepository.save(f);

                    return ResponseEntity.ok()
                            .body( f );
                }
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFav(@PathVariable UUID id, Authentication authentication){
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            boolean estaAnime=false, estaFavs=false;
            for (Anime a : animeRepository.findAll()){
                if(a.animeid.equals(id)) {
                    estaAnime = true;
                }
            }
            for (Favorite f : favoriteRepository.findAll()) {
                if (f.animeid.equals(id)){
                    estaFavs = true;
                }
            }

            if(!estaAnime){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body( Error.message("Aquesta id no perteneix a cap anime existent") );
            } else {
                if (!estaFavs) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body( Error.message("Aquest anime no esta en favorits") );
                } else {
                    Favorite f = new Favorite();
                    f.animeid = id;
                    f.usersid = autorizado.usersid;
                    favoriteRepository.delete(f);

                    return ResponseEntity.ok()
                            .body( Error.message( "S'ha eliminat del favorits l'anime amb id '" + id + "'" ) );
                }
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }

}