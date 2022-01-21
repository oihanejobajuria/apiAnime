package com.example.anime.controller;


import com.example.anime.domain.dto.*;
import com.example.anime.domain.dto.Error;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.projection.ProjectionFav_animeidUserid;
import com.example.anime.domain.model.projection.ProjectionFav_setAnime;
import com.example.anime.domain.model.projection.ProjectionUsers_idUsername;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.FavoriteRepository;
import com.example.anime.repository.UsersRespository;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/users")  // este mapeado funciona con esto
public class UsersController {

    @Autowired private UsersRespository usersRepository;
    @Autowired private FavoriteRepository favoriteRepository;
    @Autowired private AnimeRepository animeRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;


    // users  ----------------------------------------------------------------------------------------------

    @GetMapping("/")
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok()
                .body( new ResponseList(usersRepository.findBy(ProjectionUsers_idUsername.class) ));
    }

    @PostMapping(path =  "/register")
    public ResponseEntity<?> register(@RequestBody UserRegister userRegister) {
        if (usersRepository.findByUsername(userRegister.username) == null) {
            Users user = new Users();
            user.username = userRegister.username;
            user.password = passwordEncoder.encode(userRegister.password);

            Users savedFile = usersRepository.save(user);
            FileResult fileResult = new FileResult(savedFile.usersid, savedFile.password);

            return ResponseEntity.ok().body(userRegister);
        }
        //error 409
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Error.message( "Ja existeix un usuari amb el nom '" + userRegister.username + "'" ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable UUID id){
        Users u = usersRepository.findById(id).orElse(null);

        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat el user amd id '" + id  + "'"));
        }
        usersRepository.delete(u);
        return ResponseEntity.ok()
                .body( Error.message( "S'ha eliminat l'usuari amd id '" + id + "'" ));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(){
        usersRepository.deleteAll();
        return ResponseEntity.ok().body( Error.message( "S'ha eliminat tots els users" ) );
    }


    // favorites  ----------------------------------------------------------------------------------------------


    @GetMapping("/favorites")
    public ResponseEntity<?> todosFav(Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            return ResponseEntity.ok()
                    .body( usersRepository.findByUsername(authentication.getName(), ProjectionFav_setAnime.class) );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }

    @PostMapping(path =  "/favorites")
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

    @DeleteMapping("/favorites/{id}")
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