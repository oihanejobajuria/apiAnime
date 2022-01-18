package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Genre;
import com.example.anime.domain.model.projection.ProjectionGetAllGenre;
import com.example.anime.repository.FavoriteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/genres")  // este mapeado funciona con esto
public class FavoriteController {
    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> todos(){
//        return ResponseEntity.ok().body( new ResponseList(genreRepository.findBy(ProjectionGetAllGenre.class)) );
        return ResponseEntity.ok().body( new ResponseList(favoriteRepository.findBy()) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> id(@PathVariable UUID id){
        Genre genre = favoriteRepository.findById(id).orElse(null);

        if (genre == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat l'autor amd id " + id));
        else
            return ResponseEntity.ok().body(favoriteRepository.findByGenreid(id, ProjectionGetAllGenre.class));
    }
}
