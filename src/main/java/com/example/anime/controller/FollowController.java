package com.example.anime.controller;


import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestFavorite;
import com.example.anime.domain.dto.RequestFollow;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.UsersFollow;
import com.example.anime.domain.model.projection.ProjectionFav_setAnime;
import com.example.anime.domain.model.projection.ProjectionFollow_setUsers;
import com.example.anime.domain.model.projection.ProjectionFollowby_setUsers;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.FavoriteRepository;
import com.example.anime.repository.FollowRepository;
import com.example.anime.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class FollowController {

    @Autowired private UsersRepository usersRepository;
    @Autowired private FollowRepository followRepository;
    @Autowired private AnimeRepository animeRepository;


    @GetMapping("/follow/")
    public ResponseEntity<?> todosFollow(Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            return ResponseEntity.ok()
                    .body( usersRepository.findByUsername(authentication.getName(), ProjectionFollow_setUsers.class) );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }

    @GetMapping("/followby/")
    public ResponseEntity<?> todosFollowby(Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            return ResponseEntity.ok()
                    .body( usersRepository.findByUsername(authentication.getName(), ProjectionFollowby_setUsers.class) );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }

    @PostMapping("/follow/")
    public ResponseEntity<?> addFollow(@RequestBody RequestFollow requestFollow, Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            Users userFollow = usersRepository.findByUsername(requestFollow.username);

            if(userFollow==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body( Error.message("Aquest username no perteneix a cap usari existent") );
            } else {
                UsersFollow u = new UsersFollow();
                u.userbase = autorizado.usersid;
                u.followers_list = userFollow.usersid;
                followRepository.save(u);

                return ResponseEntity.ok()
                        .body(u);
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteFav(@PathVariable UUID id, Authentication authentication){
//        if (authentication.getName() != null) {
//            Users autorizado = usersRepository.findByUsername(authentication.getName());
//
//            boolean estaAnime=false, estaFavs=false;
//            for (Anime a : animeRepository.findAll()){
//                if(a.animeid.equals(id)) {
//                    estaAnime = true;
//                }
//            }
//            for (Favorite f : favoriteRepository.findAll()) {
//                if (f.animeid.equals(id)){
//                    estaFavs = true;
//                }
//            }
//
//            if(!estaAnime){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body( Error.message("Aquesta id no perteneix a cap anime existent") );
//            } else {
//                if (!estaFavs) {
//                    return ResponseEntity.status(HttpStatus.CONFLICT).body( Error.message("Aquest anime no esta en favorits") );
//                } else {
//                    Favorite f = new Favorite();
//                    f.animeid = id;
//                    f.usersid = autorizado.usersid;
//                    favoriteRepository.delete(f);
//
//                    return ResponseEntity.ok()
//                            .body( Error.message( "S'ha eliminat del favorits l'anime amb id '" + id + "'" ) );
//                }
//            }
//
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
//    }
}