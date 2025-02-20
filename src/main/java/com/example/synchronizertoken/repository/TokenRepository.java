package com.example.synchronizertoken.repository;

import com.example.synchronizertoken.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findBySessionId(String sessionId);

    @Modifying
    @Query("DELETE FROM Token t WHERE t.sessionId = :sessionId")
    void deleteBySessionId(String sessionId);

    @Modifying
    @Query("DELETE FROM Token t WHERE t.expiryTime < :now")
    void deleteByExpiryTimeBefore(LocalDateTime now);
}