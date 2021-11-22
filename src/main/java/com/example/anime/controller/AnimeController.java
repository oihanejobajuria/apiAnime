package com.example.anime.controller;

import com.example.anime.domain.model.Anime;
import com.example.anime.domain.dto.Message;
import com.example.anime.repository.AnimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/anime")  // este mapeado funciona con esto
public class AnimeController {

    private final AnimeRepository animeRepository;
    public AnimeController(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @GetMapping("/")
    public List<Anime> todos() {
        return animeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnime(@PathVariable UUID id) {
        Anime comprobar = animeRepository.findById(id).orElse(null);

        if (comprobar == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Message.message("No s'ha trobat l'anime amd id " + id));
        else
            return ResponseEntity.ok().body(comprobar);
    }


    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody Anime anime) {
        for (Anime a : animeRepository.findAll()){
            if(a.name.equals(anime.getName()))
                // error 409
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Message.message("Ja existeix un anime amb el nom '" + anime.getName()));
        }
        return ResponseEntity.ok().body(animeRepository.save(anime));
    }




}
