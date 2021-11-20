package com.example.anime.controller;

import com.example.anime.CustomException;
import com.example.anime.domain.model.Anime;
import com.example.anime.repository.AnimeRepository;
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
    public Anime getAnime(@PathVariable UUID id) throws CustomException {
        Anime comprobar = animeRepository.getById(id);

        if (comprobar == null) throw new CustomException("No s'ha trobat l'anime amd id " + id); // error 404 si no existe
        else return comprobar;
    }


    @PostMapping("/")
    public Anime createAnime(@RequestBody Anime anime){
        // si el nombre es igual error 409
        return animeRepository.save(anime);
    }




}
