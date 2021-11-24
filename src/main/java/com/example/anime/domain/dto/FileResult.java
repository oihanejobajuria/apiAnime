package com.example.anime.domain.dto;

import java.util.UUID;

public class FileResult {
    public UUID fileid;
    public String contenttype;

    public FileResult(UUID fileid, String contenttype) {
        this.fileid = fileid;
        this.contenttype = contenttype;
    }
}

