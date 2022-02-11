package com.example.anime.controller;

import com.example.anime.domain.dto.*;
import com.example.anime.domain.dto.Error;
import com.example.anime.domain.model.MyFile;
import com.example.anime.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired private FileRepository fileRepository;


    @GetMapping("/")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok()
                .body(new ResponseList(fileRepository.findBy()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id) {
        MyFile file = fileRepository.findById(id).orElse(null);

        if (file==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("File not found"));

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }


    @PostMapping("/")
    public ResponseEntity<?> upload(@RequestParam("files")MultipartFile uploadedFile){
        try{
            MyFile file = new MyFile();
            file.contenttype = uploadedFile.getContentType();
            file.data = uploadedFile.getBytes();

            MyFile savedFile = fileRepository.save(file);
            FileResult fileResult = new FileResult(savedFile.fileid, savedFile.contenttype);

            return ResponseEntity.ok().body(fileResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFiles(@PathVariable UUID id){
        MyFile comprobar = fileRepository.findById(id).orElse(null);

        if (comprobar==null) {
            // error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body( Error.message("No s'ha trobat l'arxiu amb id '" + id + "'"));
        }

        fileRepository.delete(comprobar);
        return ResponseEntity.ok().body( Error.message("S'ha eliminat l'arxiu amb id '" + id  + "'") );

    }


    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(){
        fileRepository.deleteAll();
        return ResponseEntity.ok().body( Error.message("S'ha eliminat tots els arxius" ));
    }
}
