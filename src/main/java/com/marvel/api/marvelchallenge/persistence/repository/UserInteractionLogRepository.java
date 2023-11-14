package com.marvel.api.marvelchallenge.persistence.repository;

import com.marvel.api.marvelchallenge.persistence.entity.UserInteractionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInteractionLogRepository extends JpaRepository<UserInteractionLog, Long> {

    Page<UserInteractionLog> findByUsername(Pageable pageable, String user);
}
