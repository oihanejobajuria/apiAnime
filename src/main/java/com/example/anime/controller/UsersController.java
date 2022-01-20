package com.example.anime.controller;


import com.example.anime.domain.dto.*;
import com.example.anime.domain.dto.Error;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.projection.ProjectionAnime_idNameImg;
import com.example.anime.domain.model.projection.ProjectionFav_setAnime;
import com.example.anime.domain.model.projection.ProjectionUsers_idUsername;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.FavoriteRepository;
import com.example.anime.repository.UsersRespository;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

            Favorite f = new Favorite();
            f.animeid = requestFavorite.animeid;
            f.usersid = autorizado.usersid;
            favoriteRepository.save(f);
            return ResponseEntity.ok()
                    .body( usersRepository.findByUsername(authentication.getName(), ProjectionFav_setAnime.class) );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );

    }

//    @DeleteMapping("/favorites/{id}")
//    public ResponseEntity<?> deleteUsers(@PathVariable UUID id){
//        Users u = usersRepository.findById(id).orElse(null);
//
//        if (u == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(Error.message("No s'ha trobat el user amd id '" + id  + "'"));
//        }
//        usersRepository.delete(u);
//        return ResponseEntity.ok()
//                .body( Error.message( "S'ha eliminat l'usuari amd id '" + id + "'" ));
//    }






}