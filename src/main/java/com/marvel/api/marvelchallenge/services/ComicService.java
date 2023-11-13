package com.marvel.api.marvelchallenge.services;

import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.ComicDTO;
import com.marvel.api.marvelchallenge.persistence.dto.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComicService {
    List<ComicDTO> findAll(Pagination pagination, Long characterId);

    ComicDTO findComicById(Long comicId);
}
