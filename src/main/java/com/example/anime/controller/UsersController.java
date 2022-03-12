package com.example.anime.controller;

import com.example.anime.domain.dto.*;
import com.example.anime.domain.dto.Error;
import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.projection.ProjectionUsers_idUsername;
import com.example.anime.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired private UsersRepository usersRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/")
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok()
                .body( new ResponseList(usersRepository.findBy(ProjectionUsers_idUsername.class) ));
    }

    @GetMapping("/login")
    public ResponseEntity<?> isLog() {
        return ResponseEntity.ok().build();
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
                    .body(Error.message("No s'ha trobat el user amb id '" + id  + "'"));
        }
        usersRepository.delete(u);
        return ResponseEntity.ok()
                .body( Error.message( "S'ha eliminat l'usuari amb id '" + id + "'" ));
    }


    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(){
        usersRepository.deleteAll();
        return ResponseEntity.ok().body( Error.message( "S'ha eliminat tots els usuaris" ) );
    }

}