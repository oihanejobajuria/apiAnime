package com.example.anime.controller;


import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestFavorite;
import com.example.anime.domain.dto.RequestFollow;
import com.example.anime.domain.model.*;
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

    @DeleteMapping("/unfollow/{id}")
    public ResponseEntity<?> deleteFav(@PathVariable UUID id, Authentication authentication){
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            Users userFollow = usersRepository.findById(id).orElse(null);

            boolean existsUser = false;

            for (UsersFollow u : followRepository.findAll()) {
                if (u.followers_list.equals(id)) {
                    existsUser = true;
                }
            }

            if(userFollow==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body( Error.message("Aquest username no perteneix a cap usari existent") );
            }

            if(existsUser) {
                UsersFollow u = new UsersFollow();
                u.userbase = autorizado.usersid;
                u.followers_list = userFollow.usersid;
                followRepository.delete(u);

                return ResponseEntity.ok()
                        .body(Error.message("Has deixat de seguir a " + userFollow.username));
            } else
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Error.message("No segueixes a " + userFollow.username));

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }
}