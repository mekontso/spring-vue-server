package com.mas.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class Token {
    private static final String SECRET_KEY = "357638792F423F4528482B4D6251655468576D597133743677397A2443264629";
    @Getter
    private final String token;

    private Token(String token){
        this.token = token;
    }

    public static Token of(Long userAppId, Long validityInMinutes, String secret){
        var issueDate = Instant.now();

        return new Token(
                Jwts.builder()
                        .claim("user_id", userAppId)
                        .setIssuedAt(Date.from(issueDate))
                        .setExpiration(Date.from(issueDate.plus(validityInMinutes, ChronoUnit.MINUTES)))
                        .signWith(getSignInKey(secret), SignatureAlgorithm.HS256)
                        .compact()
        );

    }
    private static Key getSignInKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

