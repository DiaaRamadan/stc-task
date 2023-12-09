package com.stc.assessment.controllers;

import com.stc.assessment.dto.request.CreateFolderRequest;
import com.stc.assessment.entities.Item;
import com.stc.assessment.services.FolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "folders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping
    public ResponseEntity<Item> createSpace(@RequestBody @Valid CreateFolderRequest folderRequest) {
        Item folder = folderService.createFolder(folderRequest);
        return ResponseEntity.ok(folder);
    }

}
