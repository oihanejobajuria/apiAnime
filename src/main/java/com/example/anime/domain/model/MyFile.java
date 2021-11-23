package com.example.anime.domain.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "file")
public class MyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID fileid;
    public String contenttype;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] data;

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
