package com.example.synchronizertoken.service;

import com.example.synchronizertoken.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor // Generates a constructor with required arguments (dependencies)
@Service
public class TokenCleanupService {

    private final TokenRepository tokenRepository;

    // Run the cleanup task every 5 minutes
    @Scheduled(fixedRate = 5 * 60 * 1000) // 5 minutes in milliseconds
    @Transactional
    public void cleanupExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        Integer deleted = tokenRepository.numberOfTokensToDelete(now); // Delete expired tokens
        tokenRepository.deleteByExpiryTimeBefore(now); // Delete expired tokens
        System.out.println(deleted + " expired tokens cleaned up at: " + now);
    }
}
