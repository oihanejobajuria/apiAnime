package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Author;
import com.example.anime.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/authors")  // este mapeado funciona con esto
public class AuthorController {
    private final AuthorRepository authorRepository;
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok().body( new ResponseList(authorRepository.findBy()) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> id(@PathVariable UUID id){
        Author author = authorRepository.findById(id).orElse(null);

        if (author == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat l'autor amd id " + id));
        else
            return ResponseEntity.ok().body(author);
    }
}
