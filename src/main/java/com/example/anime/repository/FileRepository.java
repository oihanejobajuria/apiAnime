package com.example.anime.repository;

import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<MyFile, UUID> {
    @Query("select fileid from MyFile")
    List<String> getFileIds();
}
