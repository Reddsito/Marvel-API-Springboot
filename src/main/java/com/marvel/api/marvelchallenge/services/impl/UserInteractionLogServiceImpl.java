package com.marvel.api.marvelchallenge.services.impl;

import com.marvel.api.marvelchallenge.persistence.dto.UserInteractionLogDTO;
import com.marvel.api.marvelchallenge.mappers.UserInteractionLogMapper;
import com.marvel.api.marvelchallenge.persistence.repository.UserInteractionLogRepository;
import com.marvel.api.marvelchallenge.services.UserInteractionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInteractionLogServiceImpl implements UserInteractionLogService {

    private final UserInteractionLogRepository userInteractionLogRepository;

    @Override
    public Page<UserInteractionLogDTO> findAll(Pageable pageable) {
        return userInteractionLogRepository.findAll(pageable)
                .map(UserInteractionLogMapper::toDto);
    }

    @Override
    public Page<UserInteractionLogDTO> findByUsername(String username, Pageable pageable) {
        return userInteractionLogRepository.findByUsername(pageable, username)
                .map(UserInteractionLogMapper::toDto);
    }
}
