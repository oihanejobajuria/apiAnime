package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.RequestViewed;
import com.example.anime.domain.model.Episode;
import com.example.anime.domain.model.Season;
import com.example.anime.domain.model.Users;
import com.example.anime.domain.model.Viewed;
import com.example.anime.domain.model.projection.ProjectionFav_setAnime;
import com.example.anime.domain.model.projection.ProjectionViewed_setEpisode;
import com.example.anime.repository.EpisodeRepository;
import com.example.anime.repository.SeasonRepository;
import com.example.anime.repository.UsersRepository;
import com.example.anime.repository.ViewedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/users/viewed")
public class ViewedController {

    @Autowired private UsersRepository usersRepository;
    @Autowired private ViewedRepository viewedRepository;
    @Autowired private EpisodeRepository episodeRepository;

    @GetMapping("/")
    public ResponseEntity<?> todosFav(Authentication authentication) {
        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            return ResponseEntity.ok()
                    .body( usersRepository.findByUsername(authentication.getName(), ProjectionViewed_setEpisode.class) );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );
    }

    @PostMapping("/")
    public ResponseEntity<?> addViewed(@RequestBody RequestViewed requestViewed, Authentication authentication) {

        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            Episode episode = episodeRepository.findById(requestViewed.episodeid).orElse(null);

            boolean isViewed = false;

//            Viewed viewedEpisode = viewedRepository.findById(episode.episodeid).orElse(null);

            for (Viewed v : viewedRepository.findAll()) {
                if (v.episodeid.equals(requestViewed.episodeid)){
                    isViewed  = true;
                }
            }

            if (episode == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("Aquesta id no perteneix a cap episodi existent"));
            }

            if (isViewed) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body( Error.message("L'episodi "+ episode.name + " ja esta vist") );
            } else {
                Viewed v = new Viewed();
                v.episodeid = requestViewed.episodeid;
                v.usersid = autorizado.usersid;
                viewedRepository.save(v);

                return ResponseEntity.ok()
                        .body(Error.message("S'ha afegit a episodis vistos el episodi " + episode.name + " amb id " + v.episodeid));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( Error.message("No estas autoritzat") );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteViewed(@PathVariable UUID id, Authentication authentication) {

        if (authentication.getName() != null) {
            Users autorizado = usersRepository.findByUsername(authentication.getName());

            Episode episode = episodeRepository.findById(id).orElse(null);

            boolean isViewed = false;

//            Viewed viewedEpisode = viewedRepository.findById(episode.episodeid).orElse(null);

            for (Viewed v : viewedRepository.findAll()) {
                if (v.episodeid.equals(id)) {
                    isViewed = true;
                }
            }

            if (episode == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("Aquesta id no perteneix a cap episodi existent"));
            }

            if (!isViewed) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Error.message("Aquest episodi no esta vist"));
            } else {
                Viewed v = new Viewed();
                v.episodeid = id;
                v.usersid = autorizado.usersid;
                viewedRepository.delete(v);

                return ResponseEntity.ok()
                        .body(Error.message("S'ha eliminat de episodis vistos el episodi " + episode.name + " amb id " + v.episodeid));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("No estas autoritzat"));
    }
}
