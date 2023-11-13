package com.marvel.api.marvelchallenge.services.impl;

import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import com.marvel.api.marvelchallenge.persistence.dto.Pagination;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.repository.CharacterRepository;
import com.marvel.api.marvelchallenge.services.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Override
    public List<CharacterDTO> findAll(Pagination pagination, String name, int[] comics, int[] series) {
        return characterRepository.findAll(pagination, name, comics, series);
    }

    @Override
    public CharacterInfoDTO findInfoById(Long characterId) {
        return characterRepository.findInfoById(characterId);
    }

}
