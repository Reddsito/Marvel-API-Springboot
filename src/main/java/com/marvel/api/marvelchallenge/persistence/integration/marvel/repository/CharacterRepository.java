package com.marvel.api.marvelchallenge.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.MarvelAPIConfig;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import com.marvel.api.marvelchallenge.persistence.dto.Pagination;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.mapper.CharacterMapper;
import com.marvel.api.marvelchallenge.services.HttpClientService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CharacterRepository {

    private final MarvelAPIConfig marvelAPIConfig;
    private final HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;
    private String characterPath;

    //Para asegurarme de que primero llegue el basePath para poder crear el characterPath
    @PostConstruct
    private void setPath(){
        characterPath = basePath.concat("/").concat("characters");
    }

    public List<CharacterDTO> findAll(Pagination pagination, String name, int[] comics, int[] series)  {
        Map<String, String> queryParams = this.getQueryParamsForFindAll(pagination, name, comics, series);

        JsonNode response = httpClientService.doGet(characterPath, queryParams, JsonNode.class);

        return CharacterMapper.toDtoList(response);
    }

    public CharacterInfoDTO findInfoById(Long characterId) {
        Map<String, String> queryParams = marvelAPIConfig.getAuthentificationQueryParams();

        String finalUrl = characterPath.concat("/").concat(Long.toString(characterId));

        JsonNode response = httpClientService.doGet(finalUrl, queryParams, JsonNode.class);

        return CharacterMapper.toInfoDtoList(response).get(0);
    }

    private Map<String, String> getQueryParamsForFindAll(Pagination pagination, String name, int[] comics, int[] series) {
        Map<String, String> queryParams = marvelAPIConfig.getAuthentificationQueryParams();

        queryParams.put("offset", Long.toString(pagination.offset()) );
        queryParams.put("limit", Long.toString(pagination.limit()) );

        if (StringUtils.hasLength(name) ) {
            queryParams.put("name", name);
        }

        if (comics != null ) {
            String comicsAsString = this.joinIntArray(comics);
            queryParams.put("comics", comicsAsString);
        }

        if (series != null ) {
            String seriesAsString = this.joinIntArray(series);
            queryParams.put("series", seriesAsString);
        }

        return queryParams;
    }

    private String joinIntArray(int[] list) {

        String[] stringArray = Arrays.stream(list)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        return String.join(",", stringArray);
    }

}

