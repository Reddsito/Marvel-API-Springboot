package com.marvel.api.marvelchallenge.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.MarvelAPIConfig;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.ComicDTO;
import com.marvel.api.marvelchallenge.persistence.dto.Pagination;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.mapper.ComicMapper;
import com.marvel.api.marvelchallenge.services.HttpClientService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ComicRepository {

    private final MarvelAPIConfig marvelAPIConfig;
    private final HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;
    private String comicPath;

    //Para asegurarme de que primero llegue el basePath para poder crear el characterPath
    @PostConstruct
    private void setPath(){
        comicPath = basePath.concat("/").concat("comics");
    }

    public List<ComicDTO> findAll(Pagination pagination, Long characterId) {
        Map<String, String> queryParams = this.getQueryParamsForFindAll(pagination, characterId);

        JsonNode response = httpClientService.doGet(comicPath, queryParams, JsonNode.class);

        return ComicMapper.toDtoList(response);
    }

    public ComicDTO findComicById(Long comicId) {
        Map<String, String> queryParams = this.marvelAPIConfig.getAuthentificationQueryParams();

        String finalUrl = comicPath.concat("/").concat(Long.toString(comicId));

        JsonNode response = httpClientService.doGet(finalUrl, queryParams, JsonNode.class);

        return ComicMapper.toDtoList(response).get(0);
    }

    private Map<String, String> getQueryParamsForFindAll(Pagination pagination, Long characterId) {
        Map<String, String> queryParams = marvelAPIConfig.getAuthentificationQueryParams();

        queryParams.put("offset", Long.toString(pagination.offset()) );
        queryParams.put("limit", Long.toString(pagination.limit()) );

        if ( characterId != null && characterId > 0 ) {
            queryParams.put("characters", Long.toString(characterId));
        }

        return queryParams;
    }

}
