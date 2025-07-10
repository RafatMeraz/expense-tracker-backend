package io.rafat.expensetracker.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.rafat.expensetracker.dto.JwtToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.access}")
    private Long accessTokenDuration;

    @Value("${jwt.expiration.refresh}")
    private Long refreshTokenDuration;

    public JwtToken generateToken(Authentication authentication) {
        String email = authentication.getName();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + (accessTokenDuration * 3600000));

        String token = Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .token(token)
                .expiration(expirationDate.getTime())
                .build();
    }

    public JwtToken generateRefreshToken(Authentication authentication) {
        String email = authentication.getName();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + (refreshTokenDuration * 3600000));

        String token = Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .token(token)
                .expiration(expirationDate.getTime())
                .build();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String getUserEmail(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;
    }
}
