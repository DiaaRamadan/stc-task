package com.stc.assessment.controllers;

import com.stc.assessment.dto.request.CreateSpaceRequest;
import com.stc.assessment.entities.Item;
import com.stc.assessment.services.SpaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "spaces", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @PostMapping
    public ResponseEntity<Item> createSpace(@RequestBody @Valid CreateSpaceRequest spaceRequest) {
        Item space = spaceService.createSpace(spaceRequest);
        return ResponseEntity.ok(space);
    }

}
