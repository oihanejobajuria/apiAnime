package com.example.anime.controller;

import com.example.anime.domain.dto.*;
import com.example.anime.domain.model.MyFile;
import com.example.anime.repository.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/files")  // este mapeado funciona con esto
public class FileController {

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/")
    public ResponseFilesResult todos(){
        ResponseFilesResult newFiles = null;

        for (MyFile a : fileRepository.findAll()){
            FileResult fileResult = new FileResult(a.fileid, a.contenttype);
            newFiles.result.add(fileResult);
        }

        return newFiles;
    }
//    @GetMapping("/")
//    public List<FileResult> todos(){
//        List<FileResult> newFiles = null;
//
//        for (MyFile a : fileRepository.findAll()){
//            FileResult fileResult = new FileResult(a.fileid, a.contenttype);
//            newFiles.add(fileResult);
//        }
//
//        return newFiles;
//    }
//    @GetMapping("/")
//    public ResponseFiles todos(){
//        return new ResponseFiles(fileRepository.findAll());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id) {
        MyFile file = fileRepository.findById(id).orElse(null);

        if (file==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.message("File not found"));

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }

    @PostMapping("/")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile uploadesdFile){
        try{
            MyFile file = new MyFile();
            file.contenttype = uploadesdFile.getContentType();
            file.data = uploadesdFile.getBytes();

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
                    .body(Message.message("No s'ha trobat el user amd id '" + id + "'"));
        }

        fileRepository.delete(comprobar);
        return ResponseEntity.ok().body( "S'ha eliminat el user amd id '" + id  + "'" );

    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(){
        fileRepository.deleteAll();
        return ResponseEntity.ok().body( "S'ha eliminat tots els files" );
    }
}
