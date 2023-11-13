package com.marvel.api.marvelchallenge.services.impl;

import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.ComicDTO;
import com.marvel.api.marvelchallenge.persistence.dto.Pagination;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.repository.ComicRepository;
import com.marvel.api.marvelchallenge.services.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComicServiceImpl implements ComicService {

    private final ComicRepository comicRepository;

    @Override
    public List<ComicDTO> findAll(Pagination pagination, Long characterId) {
        return comicRepository.findAll(pagination, characterId);
    }

    @Override
    public ComicDTO findComicById(Long comicId) {
        return comicRepository.findComicById(comicId);
    }
}
