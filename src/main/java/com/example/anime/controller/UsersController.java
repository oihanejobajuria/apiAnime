package com.example.anime.controller;

import com.example.anime.domain.dto.Message;
import com.example.anime.domain.dto.UserRegisterRequest;
import com.example.anime.domain.model.Users;
import com.example.anime.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/users")  // este mapeado funciona con esto
public class UsersController {

    private final UsersRepository usersRepository;
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequest userRegisterRequest) {

        if (userRepository.findByUsername(userRegisterRequest.username) == null) {
            User user = new User();
            user.username = userRegisterRequest.username;
            user.password = passwordEncoder.encode(userRegisterRequest.password);
            user.enabled = true;
            userRepository.save(user);
            return "OK";   // TODO
        }
        return "ERROR";    // TODO
    }

    //-----------------------------------------------------------------------------






    @GetMapping("/")
    public List<Users> todos() {
        return usersRepository.findAll();
    }


    @PostMapping("/")
    public ResponseEntity<?> createUsers(@RequestBody Users user) {
        for (Users a : usersRepository.findAll()){
            if(a.username.equals(user.getUsername()))
                // error 409
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Message.message("Ja existeix un usuari amb el nom '" + user.getUsername() + "'"));
        }
        return ResponseEntity.ok().body(usersRepository.save(user));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable UUID id){
        for (Users a : usersRepository.findAll()){
            if(a.animeid.equals(id))
                usersRepository.delete(a);
                return ResponseEntity.ok().body( "S'ha eliminat el user amd id '" + id  + "'" );
        }
        // error 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Message.message("No s'ha trobat el user amd id '" + id  + "'"));

    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(){
        usersRepository.deleteAll();
        return ResponseEntity.ok().body( "S'ha eliminat tots els users" );
    }




}
