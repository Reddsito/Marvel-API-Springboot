package com.marvel.api.marvelchallenge.persistence.integration.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterInfoDTO {
    private String imagePath;
    private String description;
}
