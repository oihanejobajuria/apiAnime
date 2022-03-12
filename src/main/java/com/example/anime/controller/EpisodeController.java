package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestEpisode;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Episode;
import com.example.anime.domain.model.Season;
import com.example.anime.domain.model.projection.ProjectionEpisode_idNameNum_listSeasonAnime;
import com.example.anime.domain.model.projection.ProjectionEpisode_idNameNum_listSeasonAnime2;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.EpisodeRepository;
import com.example.anime.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/animes/seasons/episodes")
public class EpisodeController {

    @Autowired private EpisodeRepository episodeRepository;
    @Autowired private SeasonRepository seasonRepository;
    @Autowired private AnimeRepository animeRepository;


    @GetMapping("/")
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok().body(new ResponseList(episodeRepository.findBy(ProjectionEpisode_idNameNum_listSeasonAnime.class)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEpisode(@PathVariable UUID id) {
        Episode comprobar = episodeRepository.findById(id).orElse(null);

        if (comprobar == null)
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat l'episodi amb id " + id));
        else
            return ResponseEntity.ok().body(episodeRepository.findByEpisodeid(comprobar.episodeid, ProjectionEpisode_idNameNum_listSeasonAnime2.class));
    }


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
        }

        Episode episode = new Episode();
        episode.name = requestEpisode.name;
        episode.synopsis = requestEpisode.synopsis;
        episode.link = requestEpisode.link;
        episode.seasonWithEpisodes = season;
        if (season.episodes.size() == 0) {
            episode.num = 1;
        } else {
            episode.num = season.episodes.size()+1;
        }

        episodeRepository.save(episode);

        return ResponseEntity.ok()
                .body( episodeRepository.findByEpisodeid(episode.episodeid, ProjectionEpisode_idNameNum_listSeasonAnime2.class) );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEpisode(@PathVariable UUID id){
        Episode episode = episodeRepository.findById(id).orElse(null);

        if (episode==null){
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Error.message("No s'ha trobat l'episodi amb l'id '" + id  + "'"));
        }

        episodeRepository.delete(episode);
        return ResponseEntity.ok().body(Error.message( "S'ha eliminat l'episodi amb id '" + id + "'"));

    }

}
