package com.example.anime.controller;

import com.example.anime.domain.model.Doblador;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.DobladorRepository;

public class DobladorController {
    private final DobladorRepository dobladorRepository;

    public DobladorController(DobladorRepository dobladorRepository) {
        this.dobladorRepository = dobladorRepository;
    }


}
