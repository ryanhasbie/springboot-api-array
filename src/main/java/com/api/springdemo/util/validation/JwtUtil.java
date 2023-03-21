package com.api.springdemo.util.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt_secret}")
    private String jwtSecret;
    @Value("${jwt_expire}")
    private Integer jwtExpiration;

    public String generateToken(String subject) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration));
        return builder.compact();
    };

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Invalid jwt token!");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Jwt token expired");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Jwt token unsupported!");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Jwt token invalid");
        } catch (SignatureException e) {
            throw new RuntimeException("Jwt signature not match!");
        }
    };
}
