package com.example.anime.domain.dto;

import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.MyFile;

import java.util.List;

public class ResponseFiles {
    public List<MyFile> result;

    public ResponseFiles(List<MyFile> result) {
        this.result = result;
    }
}

class MostrarFiles{

}
