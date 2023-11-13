package com.marvel.api.marvelchallenge.controllers;

import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import com.marvel.api.marvelchallenge.persistence.dto.Pagination;
import com.marvel.api.marvelchallenge.services.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping()
    public ResponseEntity<List<CharacterDTO>> findAll(@RequestParam(required = false) String name,
                                                      @RequestParam(required = false) int[] comics,
                                                      @RequestParam(required = false) int[] series,
                                                      @RequestParam(defaultValue = "0") long offset,
                                                      @RequestParam(defaultValue = "10") long limit
    )
    {

            Pagination pagination = new Pagination(offset, limit);
            return ResponseEntity.ok(characterService.findAll(pagination, name, comics, series));

    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterInfoDTO> findAll(@PathVariable Long characterId
    )
    {
        return ResponseEntity.ok(characterService.findInfoById(characterId));
    }



}
