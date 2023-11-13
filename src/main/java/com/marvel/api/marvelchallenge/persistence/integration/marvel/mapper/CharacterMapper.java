package com.marvel.api.marvelchallenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.marvel.api.marvelchallenge.persistence.dto.ThumbnailDTO;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.marvel.api.marvelchallenge.persistence.integration.marvel.dto.CharacterInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    public static List<CharacterDTO> toDtoList(JsonNode rootNode) {
        ArrayNode resultsNode = getResultsNode(rootNode);

        List<CharacterDTO> characters = new ArrayList<>();
        resultsNode.elements().forEachRemaining(each ->
                {
                        characters.add( CharacterMapper.toDto(each) );
                }
                );

        return characters;
    }

    public static List<CharacterInfoDTO> toInfoDtoList(JsonNode rootNode) {
        ArrayNode resultsNode = getResultsNode(rootNode);

        List<CharacterInfoDTO> characters = new ArrayList<>();
        resultsNode.elements().forEachRemaining(each ->
                {
                    characters.add( CharacterMapper.toInfoDto(each) );
                }
        );

        return characters;
    }



    private static CharacterDTO toDto(JsonNode characterNode)  {

        return CharacterDTO
                .builder()
                .id(Long.parseLong(characterNode.get("id").asText()))
                .name(characterNode.get("name").asText())
                .description(characterNode.get("description").asText())
                .modified(characterNode.get("modified").asText())
                .resourceURI(characterNode.get("resourceURI").asText())
                .build();
    }

    public static CharacterInfoDTO toInfoDto(JsonNode characterNode) {

        JsonNode thumbnailNode = characterNode.get("thumbnail");
        ThumbnailDTO thumbnailDTO = ThumbnailDTO
                .builder()
                .path(thumbnailNode.get("path").asText())
                .extension(thumbnailNode.get("extension").asText())
                .build();

        String image = thumbnailDTO.getPath().concat(".").concat(thumbnailDTO.getExtension());


        return CharacterInfoDTO
                .builder()
                .imagePath(image)
                .description(characterNode.get("description").asText())
                .build();
    }

    private static ArrayNode getResultsNode(JsonNode rootNode) {

        JsonNode dataNode = rootNode.get("data");
        return (ArrayNode) dataNode.get("results");
    }

}
