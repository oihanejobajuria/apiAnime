package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestEpisode;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Episode;
import com.example.anime.domain.model.Episode;
import com.example.anime.domain.model.Season;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.EpisodeRepository;
import com.example.anime.repository.EpisodeRepository;
import com.example.anime.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/animes/seasons/episodes")  // este mapeado funciona con esto
public class EpisodeController {

    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private AnimeRepository animeRepository;

    // enviar el animeWithEpisodes solo uuid y nombre
    @GetMapping("/")
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok().body(new ResponseList(episodeRepository.findAll()));
    }


    // enviar el animeWithEpisodes solo uuid y nombre
    @GetMapping("/{id}")
    public ResponseEntity<?> getEpisode(@PathVariable UUID id) {
        Episode comprobar = episodeRepository.findById(id).orElse(null);

        if (comprobar == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat l'episodi amd id " + id));
        else
            return ResponseEntity.ok().body(comprobar);
    }


    // no deberia pedir el numero de temporada
    // si NO hay ninguna temporada se pone 1
    // si SI hay alguna temporada se suma 1 al ultimo num puesto
    @PostMapping("/")
    public ResponseEntity<?> createEpisode(@RequestBody RequestEpisode requestEpisode) {
        Season season = seasonRepository.findById(requestEpisode.seasonid).orElse(null);

        if (season==null) {
            // error 409
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Error.message("No existeix una temporada amb el id '" + requestEpisode.seasonid + "'"));
        }

        for (Episode episode : season.episodes) {
            if(episode.name.equals(requestEpisode.name))
                // error 409
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Error.message("Ja existeix un episodi amb el nom '" + requestEpisode.name + "'"));

            if(episode.num == requestEpisode.num)
                // error 409
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Error.message("Ja existeix l'episodi " + requestEpisode.num + " de la temporada '" + season.name + "'"));
        }

        Episode episode = new Episode();
        episode.name = requestEpisode.name;
        episode.num = requestEpisode.num;
        episode.synopsis = requestEpisode.synopsis;
        episode.seasonWithEpisodes = season;

        episodeRepository.save(episode);

        return ResponseEntity.ok()
                .body( episode );
    }


    // eliminar tambien los episodios cuando elimines la season
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEpisode(@PathVariable UUID id){
        Episode episode = episodeRepository.findById(id).orElse(null);

        if (episode==null){
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat l'episodi amb l'id '" + id  + "'"));
        }

        episodeRepository.delete(episode);
        return ResponseEntity.ok().body(Error.message( "S'ha eliminat l'episodi amd id '" + id + "'"));

    }




}
