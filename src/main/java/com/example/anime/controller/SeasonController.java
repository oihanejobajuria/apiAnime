package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestSeason;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Season;
import com.example.anime.domain.model.projection.ProjectionSeason_idNameNum_listAnime;
import com.example.anime.domain.model.projection.ProjectionSeason_idNameNum_listAnime2;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/animes/seasons")
public class SeasonController {

    @Autowired private SeasonRepository seasonRepository;
    @Autowired private AnimeRepository animeRepository;


    @GetMapping("/")
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok().body(new ResponseList(seasonRepository.findBy(ProjectionSeason_idNameNum_listAnime.class)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getSeason(@PathVariable UUID id) {
        Season comprobar = seasonRepository.findById(id).orElse(null);

        if (comprobar == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat la temporada amb id " + id));
        else
            return ResponseEntity.ok().body(seasonRepository.findBySeasonid(comprobar.seasonid, ProjectionSeason_idNameNum_listAnime2.class));
    }


    @PostMapping("/")
    public ResponseEntity<?> createSeason(@RequestBody RequestSeason requestSeason) {
        Anime anime = animeRepository.findById(requestSeason.animeid).orElse(null);

        if (anime==null) {
            // error 409
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Error.message("No existeix un anime amb el id '" + requestSeason.animeid + "'"));
        }

        for (Season s : anime.seasons) {
            if(s.name.equals(requestSeason.name))
                // error 409
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Error.message("Ja existeix una temporada amb el nom '" + requestSeason.name + "'"));
        }

        Season s = new Season();
        s.name = requestSeason.name;
        s.animeWithSeasons = anime;
        if(anime.seasons.size() == 0) {
            s.num = 1;
        } else {
            s.num = anime.seasons.size()+1;
        }

        seasonRepository.save(s);

        return ResponseEntity.ok()
                .body( seasonRepository.findBySeasonid(s.seasonid, ProjectionSeason_idNameNum_listAnime2.class) );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeason(@PathVariable UUID id){
        Season season = seasonRepository.findById(id).orElse(null);

        if (season==null){
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat la temporada amb l'id '" + id  + "'"));
        }

        seasonRepository.delete(season);
        return ResponseEntity.ok().body(Error.message( "S'ha eliminat la temporada amb id '" + id + "'"));

    }

}
