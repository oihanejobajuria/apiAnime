package com.example.anime.domain.dto;

import java.util.UUID;

public class FileResult {
    public UUID fileid;
    public String contenttype;

    public FileResult(UUID fileid, String contenttype) {
        this.fileid = fileid;
        this.contenttype = contenttype;
    }

    public UUID getFileid() {
        return fileid;
    }

    public void setFileid(UUID fileid) {
        this.fileid = fileid;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }
}

