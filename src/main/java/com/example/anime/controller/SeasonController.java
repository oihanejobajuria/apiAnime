package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestSeason;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.Season;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/animes/season")  // este mapeado funciona con esto
public class SeasonController {

    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private AnimeRepository animeRepository;

    // enviar el animeWithSeasons solo uuid y nombre
    @GetMapping("/")
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok().body(new ResponseList(seasonRepository.findAll()));
    }


    // enviar el animeWithSeasons solo uuid y nombre
    @GetMapping("/{id}")
    public ResponseEntity<?> getSeason(@PathVariable UUID id) {
        Season comprobar = seasonRepository.findById(id).orElse(null);

        if (comprobar == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat la temporada amd id " + id));
        else
            return ResponseEntity.ok().body(comprobar);
    }


    // no deberia pedir el numero de temporada
    // si NO hay ninguna temporada se pone 1
    // si SI hay alguna temporada se suma 1 al ultimo num puesto
    @PostMapping("/")
    public ResponseEntity<?> createSeason(@RequestBody RequestSeason requestSeason) {
        Anime anime = animeRepository.findById(requestSeason.animeid).orElse(null);

        if (anime==null) {
            // error 409
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Error.message("No existeix un anime amb el id '" + requestSeason.animeid + "'"));
        }

        for (Season s : seasonRepository.findAll()){
            if(s.name.equals(requestSeason.name))
                // error 409
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Error.message("Ja existeix un anime amb el nom '" + requestSeason.name + "'"));
        }

        Season s = new Season();
        s.name = requestSeason.name;
        s.num = requestSeason.num;
        s.animeWithSeasons = anime;

        seasonRepository.save(s);

        return ResponseEntity.ok()
                .body( s );
    }


    // eliminar tambien los episodios cuando elimines la season
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeason(@PathVariable UUID id){
        Season season = seasonRepository.findById(id).orElse(null);

        if (season==null){
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat la temporada amb l'id '" + id  + "'"));
        }

        seasonRepository.delete(season);
        return ResponseEntity.ok().body(Error.message( "S'ha eliminat l'anime amd id '" + id + "'"));

    }




}
