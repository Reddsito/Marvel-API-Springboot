package com.marvel.api.marvelchallenge.persistence.integration.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterDTO {
    private Long id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
}
