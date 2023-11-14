package com.marvel.api.marvelchallenge.services;

import com.marvel.api.marvelchallenge.persistence.dto.UserInteractionLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserInteractionLogService {
    Page<UserInteractionLogDTO> findAll(Pageable pageable);

    Page<UserInteractionLogDTO> findByUsername(String username, Pageable pageable);
}
