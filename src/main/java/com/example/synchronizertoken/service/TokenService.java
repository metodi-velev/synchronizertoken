package com.example.synchronizertoken.service;

import com.example.synchronizertoken.model.Token;
import com.example.synchronizertoken.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor // Generates a constructor with required arguments (dependencies)
@Service
public class TokenService {
    private static final int TOKEN_EXPIRY_MINUTES = 5;

    private final TokenRepository tokenRepository;

    // Generate and save a token for the session
    @Transactional
    public String generateToken(String sessionId) {
        // Delete any existing token for the session
        tokenRepository.deleteBySessionId(sessionId);

        // Generate a new token
        String token = UUID.randomUUID().toString();

        // Set the expiry time
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime expiryTime = createdAt.plusMinutes(TOKEN_EXPIRY_MINUTES);

        // Save the token in the database
        Token tokenEntity = new Token();
        tokenEntity.setSessionId(sessionId);
        tokenEntity.setToken(token);
        tokenEntity.setCreatedAt(createdAt);
        tokenEntity.setExpiryTime(expiryTime);
        tokenEntity.setUsed(false); // Mark the token as unused initially
        tokenRepository.save(tokenEntity);

        return token;
    }

    // Validate the token
    @Transactional(readOnly = true)
    public boolean isValidToken(String sessionId, String requestToken) {
        // Retrieve the token from the database
        Token tokenEntity = tokenRepository.findBySessionId(sessionId).orElse(null);

        // Validate the token, check if it has expired, and ensure it is not used
        if (tokenEntity != null && tokenEntity.getToken().equals(requestToken)) {
            LocalDateTime now = LocalDateTime.now();
            return now.isBefore(tokenEntity.getExpiryTime()) && !tokenEntity.isUsed(); // Check if token is valid and unused
        }

        return false;
    }

    // Mark the token as used after successful form submission
    @Transactional
    public void markTokenAsUsed(String sessionId) {
        Token tokenEntity = tokenRepository.findBySessionId(sessionId).orElse(null);
        if (tokenEntity != null) {
            tokenEntity.setUsed(true); // Mark the token as used
            tokenRepository.save(tokenEntity);
        }
    }

    @Transactional
    public void deleteToken(String sessionId) {
        // Delete any existing token for the session
        tokenRepository.deleteBySessionId(sessionId);
    }
}