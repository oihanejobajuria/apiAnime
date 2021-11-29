package com.example.anime.domain.dto;

import java.util.List;

public class ResponseList {  // nueva version
    public List<?> result;

    public ResponseList(List<?> result) {
        this.result = result;
    }
}
