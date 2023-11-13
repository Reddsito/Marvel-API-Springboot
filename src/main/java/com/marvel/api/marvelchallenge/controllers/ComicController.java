package com.marvel.api.marvelchallenge.controllers;

import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.ComicDTO;
import com.marvel.api.marvelchallenge.persistence.dto.Pagination;
import com.marvel.api.marvelchallenge.services.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comics")
@RequiredArgsConstructor
public class ComicController {

    private final ComicService comicService;

    @GetMapping()
    public ResponseEntity<List<ComicDTO>> findAll(@RequestParam(required = false) Long characterId,
                                                  @RequestParam(defaultValue = "0") Long offset,
                                                  @RequestParam(defaultValue = "10") Long limit )
    {
        Pagination pagination = new Pagination(offset, limit);
        return ResponseEntity.ok(comicService.findAll(pagination, characterId));
    }

    @GetMapping("/{comicId}")
    public ResponseEntity<ComicDTO> findAll(@PathVariable Long comicId)
    {
        return ResponseEntity.ok(comicService.findComicById(comicId));
    }
}
