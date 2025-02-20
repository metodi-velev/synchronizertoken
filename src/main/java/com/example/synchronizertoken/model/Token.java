package com.example.synchronizertoken.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-args constructor
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiryTime;

    @Column(nullable = false)
    private boolean used; // New field to track token usage
}