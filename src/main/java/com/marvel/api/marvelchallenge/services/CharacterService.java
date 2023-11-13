package com.marvel.api.marvelchallenge.services;

import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import com.marvel.api.marvelchallenge.persistence.dto.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterService {
    List<CharacterDTO> findAll(Pagination pagination, String name, int[] comics, int[] series);

    CharacterInfoDTO findInfoById(Long characterId);
}
