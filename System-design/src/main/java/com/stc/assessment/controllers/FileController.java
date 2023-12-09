package com.stc.assessment.controllers;

import com.stc.assessment.dto.request.CreateFileRequest;
import com.stc.assessment.dto.response.FileMetaData;
import com.stc.assessment.entities.File;
import com.stc.assessment.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<File> saveFile(@RequestParam("spaceId") Long spaceId,
                                         @RequestParam(value = "folderId", required = false) Long folderId,
                                         @RequestParam("file") MultipartFile file) throws IOException {
        CreateFileRequest fileRequest = new CreateFileRequest(file, spaceId, folderId);
        File saveFile = fileService.saveFile(fileRequest);
        return ResponseEntity.ok(saveFile);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable Long id) {
        File file = fileService.getFileById(id);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .body(file.getBinaries());
    }

    @GetMapping(value = "/meta-data/{id}")
    public ResponseEntity<FileMetaData> getFileMetaData(@PathVariable Long id) {
        FileMetaData fileMetaData = fileService.getFileMetaData(id);
        return ResponseEntity.ok(fileMetaData);
    }

}
