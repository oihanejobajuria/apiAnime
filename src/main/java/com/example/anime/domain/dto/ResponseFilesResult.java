package com.example.anime.domain.dto;

import com.example.anime.domain.dto.FileResult;

import java.util.List;

public class ResponseFilesResult {
    public List<FileResult> result;

    public ResponseFilesResult(List<FileResult> result) {
        this.result = result;
    }

    public List<FileResult> getResult() {
        return result;
    }

    public void setResult(List<FileResult> result) {
        this.result = result;
    }
}
