package com.marvel.api.marvelchallenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.marvel.api.marvelchallenge.persistence.dto.ThumbnailDTO;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.ComicDTO;

import java.util.ArrayList;
import java.util.List;

public class ComicMapper {

    public static List<ComicDTO> toDtoList(JsonNode rootNode) {
        ArrayNode resultsNode = getResultsNode(rootNode);

        List<ComicDTO> comics = new ArrayList<>();
        resultsNode.elements().forEachRemaining(each ->
                {
                    comics.add( ComicMapper.toDto(each) );
                }
        );

        return comics;
    }

    private static ComicDTO toDto(JsonNode characterNode)  {

        JsonNode thumbnailNode = characterNode.get("thumbnail");
        ThumbnailDTO thumbnailDTO = ThumbnailDTO
                .builder()
                .path(thumbnailNode.get("path").asText())
                .extension(thumbnailNode.get("extension").asText())
                .build();

        return ComicDTO
                .builder()
                .id(Long.parseLong(characterNode.get("id").asText()))
                .title(characterNode.get("title").asText())
                .description(characterNode.get("description").asText())
                .modified(characterNode.get("modified").asText())
                .thumbnail(thumbnailDTO)
                .build();
    }

    private static ArrayNode getResultsNode(JsonNode rootNode) {

        JsonNode dataNode = rootNode.get("data");
        return (ArrayNode) dataNode.get("results");
    }

}
