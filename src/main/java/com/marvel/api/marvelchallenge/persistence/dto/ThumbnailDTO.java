package com.marvel.api.marvelchallenge.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThumbnailDTO {
    private String path;
    private String extension;
}
