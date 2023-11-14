package com.marvel.api.marvelchallenge.persistence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class UserInteractionLogDTO {
    private Long id;
    private String url;
    private String httpMethod;
    private String username;
    private String remoteAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime timestamp;


}
