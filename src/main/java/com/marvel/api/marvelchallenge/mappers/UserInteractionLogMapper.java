package com.marvel.api.marvelchallenge.mappers;

import com.marvel.api.marvelchallenge.persistence.dto.UserInteractionLogDTO;
import com.marvel.api.marvelchallenge.persistence.entity.UserInteractionLog;

public class UserInteractionLogMapper {

    public static UserInteractionLogDTO toDto(UserInteractionLog entity) {
        if(entity == null) return null;
        return UserInteractionLogDTO
                    .builder()
                    .id(entity.getId())
                    .url(entity.getUrl())
                    .httpMethod(entity.getHttpMethod())
                    .username(entity.getUsername())
                    .timestamp(entity.getTimestamp())
                    .remoteAddress(entity.getRemoteAddress())
                    .build();
    }
}
