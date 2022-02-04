package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Genre;
import com.example.anime.domain.model.projection.ProjectionGenre_idLabel_setAnime;
import com.example.anime.domain.model.projection.ProjectionGenre_idLabelImg_setAnime;
import com.example.anime.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/genres")  // este mapeado funciona con esto
public class GenreController {
    @Autowired private GenreRepository genreRepository;

    @GetMapping("/")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok().body( new ResponseList(genreRepository.findBy(ProjectionGenre_idLabel_setAnime.class)) );
//        return ResponseEntity.ok().body( new ResponseList(genreRepository.findBy()) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> id(@PathVariable UUID id){
        Genre genre = genreRepository.findById(id).orElse(null);

        if (genre == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat l'autor amd id " + id));
        else
            return ResponseEntity.ok().body(genreRepository.findByGenreid(id, ProjectionGenre_idLabelImg_setAnime.class));
    }
}
