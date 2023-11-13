package com.marvel.api.marvelchallenge.persistence.repository;

import com.marvel.api.marvelchallenge.persistence.entity.UserInteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInteractionLogRepository extends JpaRepository<UserInteractionLog, Long> {
}
