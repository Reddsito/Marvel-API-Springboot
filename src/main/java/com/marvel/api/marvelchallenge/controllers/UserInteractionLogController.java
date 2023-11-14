package com.marvel.api.marvelchallenge.controllers;

import com.marvel.api.marvelchallenge.persistence.dto.UserInteractionLogDTO;
import com.marvel.api.marvelchallenge.services.UserInteractionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users-interactions")
@RequiredArgsConstructor
public class UserInteractionLogController {



    private final UserInteractionLogService userInteractionLogService;

    @PreAuthorize("hasAuthority('user-interaction:read-all')")
    @GetMapping
    public ResponseEntity<Page<UserInteractionLogDTO>> findAll(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ) {

        Pageable pageable = this.buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findAll(pageable));
    }

    @PreAuthorize("hasAuthority('user-interaction:read-by-username') || @interactionLogValidator.validate(#username)")
    @GetMapping("/{username}")
    public ResponseEntity<Page<UserInteractionLogDTO>> findByUsername(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Pageable pageable = this.buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findByUsername(username, pageable));
    }

    private Pageable buildPageable(int offset, int limit) {
        Pageable pageable;
        if ( offset < 0 ) {
            throw new IllegalArgumentException("El atributo offset no puede ser menor a cero");
        }

        if ( limit <= 0 ) {
            throw new IllegalArgumentException("El atributo limit no puede ser menor o igual cero");
        }

        if( offset == 0 ) pageable = PageRequest.of(0, limit);
        else pageable = PageRequest.of(offset / limit, limit);

        return pageable;
    }

}
