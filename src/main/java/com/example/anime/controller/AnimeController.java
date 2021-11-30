package com.example.anime.controller;

import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.dto.Error;
import com.example.anime.repository.AnimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/animes")  // este mapeado funciona con esto
public class AnimeController {

    private final AnimeRepository animeRepository;
    public AnimeController(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

//    @GetMapping("/")
//    public ResponseEntity<?> todos() {
//        return ResponseEntity.ok().body(new ResponseList(animeRepository.findby()));  // version 2
//    }
    @GetMapping("/")
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok().body(new ResponseList(animeRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnime(@PathVariable UUID id) {
        Anime comprobar = animeRepository.findById(id).orElse(null);

        if (comprobar == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat l'anime amd id " + id));
        else
            return ResponseEntity.ok().body(comprobar);
    }


    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody Anime anime) {
        for (Anime a : animeRepository.findAll()){
            if(a.name.equals(anime.name))
                // error 409
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Error.message("Ja existeix un anime amb el nom '" + anime.getName() + "'"));
        }
        animeRepository.save(anime);
        return ResponseEntity.ok().body(anime);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable UUID id){
        Anime file = animeRepository.findById(id).orElse(null);

        if (file==null){
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat l'anime amd id '" + id  + "'"));
        }

        animeRepository.delete(file);
        return ResponseEntity.ok().body(Error.message( "S'ha eliminat l'anime amd id '" + id + "'"));

    }




}
