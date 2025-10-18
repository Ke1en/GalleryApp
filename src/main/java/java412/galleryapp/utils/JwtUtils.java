package java412.galleryapp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private String jwtSecret = "VerySecretKey12345678901234567890"; // Только для примера, храните безопасно
    private long jwtExpirationMs = 86400000; // 1 день

    private Key key;

    public JwtUtils() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes()); // Создаем ключ из секрета
    }

    public String generateToken(String username) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token) {

        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();

    }

    public boolean validateToken(String token) {
        try {

            Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
