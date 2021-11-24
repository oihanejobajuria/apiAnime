package com.example.anime.controller;

import com.example.anime.domain.dto.Message;
import com.example.anime.domain.dto.ResponseFiles;
import com.example.anime.domain.model.MyFile;
import com.example.anime.repository.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/files")  // este mapeado funciona con esto
public class FileController {

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/")
    public ResponseFiles todos(){
        return new ResponseFiles(fileRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id) {
        MyFile file = fileRepository.findById(id).orElse(null);

        if (file==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }

    @PostMapping("/")
    public String upload(@RequestParam("file")MultipartFile uploadesdFile){
        try{
//            System.out.println(uploadesdFile.getOriginalFilename() + " , " + uploadesdFile.getContentType());
            MyFile file = new MyFile();
            file.contenttype = uploadesdFile.getContentType();
            file.data = uploadesdFile.getBytes();

//            fileRepository.save(file);
            return fileRepository.save(file).fileid.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable UUID id){
        for (MyFile a : fileRepository.findAll()){
            if(a.fileid.equals(id))
                fileRepository.delete(a);
            return ResponseEntity.ok().body( "S'ha eliminat el user amd id '" + id  + "'" );
        }
        // error 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Message.message("No s'ha trobat el user amd id '" + id  + "'"));

    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(){
        fileRepository.deleteAll();
        return ResponseEntity.ok().body( "S'ha eliminat tots els files" );
    }
}
