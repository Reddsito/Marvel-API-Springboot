package com.marvel.api.marvelchallenge.persistence.integration.marvel.dto;

import com.marvel.api.marvelchallenge.persistence.dto.ThumbnailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComicDTO {
    private Long id;
    private String title;
    private String description;
    private String modified;

    private ThumbnailDTO thumbnail;

}
